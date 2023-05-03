package bojan.sampleJavaApp.CarRegistry.Adapters.Database.Repositories;

import bojan.sampleJavaApp.CarRegistry.Domain.Entities.ClientEntity;
import bojan.sampleJavaApp.CarRegistry.Domain.Exceptions.MissingClientException;
import bojan.sampleJavaApp.CarRegistry.Domain.Repositories.ClientListRepository;
import bojan.sampleJavaApp.CarRegistry.Domain.Repositories.ClientRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class DatabaseClientRepository implements ClientRepository, ClientListRepository {

    private final bojan.sampleJavaApp.CarRegistry.Adapters.Database.Repositories.JpaData.ClientRepository jpaDataRepository;

    @Override
    public ClientEntity save(ClientEntity clientEntity) {
        return jpaDataRepository.save(clientEntity);
    }

    @Override
    public void delete(UUID clientId) {
        jpaDataRepository.deleteById(clientId);
    }

    @Override
    public @NonNull ClientEntity get(UUID id) throws MissingClientException {
        return jpaDataRepository.findById(id).orElseThrow(MissingClientException::new);
    }

    @Override
    public List<ClientEntity> getClients(int pageNumber, int itemsPerPage) {
        return jpaDataRepository.findAll(PageRequest.of(pageNumber, itemsPerPage)).toList();
    }
}
