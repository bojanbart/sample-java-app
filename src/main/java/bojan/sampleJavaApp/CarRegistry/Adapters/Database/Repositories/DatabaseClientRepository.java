package bojan.sampleJavaApp.CarRegistry.Adapters.Database.Repositories;

import bojan.sampleJavaApp.CarRegistry.Domain.Entities.Client;
import bojan.sampleJavaApp.CarRegistry.Domain.Exceptions.MissingClientException;
import bojan.sampleJavaApp.CarRegistry.Domain.Repositories.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DatabaseClientRepository implements ClientRepository {

    private final bojan.sampleJavaApp.CarRegistry.Adapters.Database.Repositories.JpaData.ClientRepository jpaDataRepository;

    @Override
    public Client save(Client client) {
        return jpaDataRepository.save(client);
    }

    @Override
    public void delete(long clientId) {
        jpaDataRepository.deleteById(clientId);
    }

    @Override
    public Client get(long id) throws MissingClientException {
        return jpaDataRepository.findById(id).orElseThrow(MissingClientException::new);
    }

    @Override
    public List<Client> getClients(int pageNumber, int itemsPerPage) {
        return jpaDataRepository.findAll(PageRequest.of(pageNumber, itemsPerPage)).getContent();
    }
}
