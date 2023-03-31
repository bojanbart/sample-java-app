package bojan.sampleJavaApp.CarRegistry.Adapters.Database.Repositories;

import bojan.sampleJavaApp.CarRegistry.Adapters.Database.DataTransformers.ClientDataTransformer;
import bojan.sampleJavaApp.CarRegistry.Domain.Entities.ClientEntity;
import bojan.sampleJavaApp.CarRegistry.Domain.Exceptions.MissingClientException;
import bojan.sampleJavaApp.CarRegistry.Domain.Repositories.ClientRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DatabaseClientRepository implements ClientRepository {

    private final bojan.sampleJavaApp.CarRegistry.Adapters.Database.Repositories.JpaData.ClientRepository jpaDataRepository;

    private final ClientDataTransformer dataTransformer;

    @Override
    public ClientEntity save(ClientEntity clientEntity) {
        return dataTransformer.toDomain(jpaDataRepository.save(dataTransformer.fromDomain(clientEntity)));
    }

    @Override
    public void delete(long clientId) {
        jpaDataRepository.deleteById(clientId);
    }

    @Override
    public @NonNull ClientEntity get(long id) throws MissingClientException {
        bojan.sampleJavaApp.CarRegistry.Adapters.Database.Entities.ClientEntity dbClient = jpaDataRepository.findById(id).orElseThrow(MissingClientException::new);

        return dataTransformer.toDomain(dbClient);
    }

    @Override
    public List<ClientEntity> getClients(int pageNumber, int itemsPerPage) {
        return jpaDataRepository.findAll(PageRequest.of(pageNumber, itemsPerPage)).map(dataTransformer::toDomain).toList();
    }
}
