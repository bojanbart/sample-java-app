package bojan.sampleJavaApp.CarRegistry.Adapters.RestApi.Services;

import bojan.sampleJavaApp.CarRegistry.Domain.Entities.ClientEntity;
import bojan.sampleJavaApp.CarRegistry.Domain.Exceptions.MissingClientException;
import bojan.sampleJavaApp.CarRegistry.Domain.Factory.ClientFactory;
import bojan.sampleJavaApp.CarRegistry.Domain.Repositories.ClientListRepository;
import bojan.sampleJavaApp.CarRegistry.Domain.Repositories.ClientRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ClientService {

    private ClientFactory factory;

    private ClientRepository repository;

    private ClientListRepository listRepository;

    public ClientEntity createNewClient(String firstname, String lastname) {
        ClientEntity client = factory.createNew(firstname, lastname);

        repository.save(client);

        return client;
    }

    public void delete(UUID id) {
        repository.delete(id);
    }

    public @NonNull ClientEntity get(UUID id) throws MissingClientException {
        return repository.get(id);
    }

    public @NonNull ClientEntity update(UUID id, String firstname, String lastname) throws MissingClientException {
        ClientEntity client = repository.get(id);

        client.update(firstname, lastname);

        repository.save(client);

        return client;
    }

    public List<ClientEntity> getClients(int pageNumber) {
        int itemsPerPage = 3;

        return listRepository.getClients(pageNumber, itemsPerPage);
    }
}
