package bojan.sampleJavaApp.CarRegistry.Domain.Services;

import bojan.sampleJavaApp.CarRegistry.Domain.Entities.Client;
import bojan.sampleJavaApp.CarRegistry.Domain.Exceptions.MissingClientException;
import bojan.sampleJavaApp.CarRegistry.Domain.Repositories.ClientRepository;
import bojan.sampleJavaApp.CarRegistry.Domain.UseCases.AddNewClientUseCase;
import bojan.sampleJavaApp.CarRegistry.Domain.UseCases.DeleteClientUseCase;
import bojan.sampleJavaApp.CarRegistry.Domain.UseCases.UpdateClientUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientService implements DeleteClientUseCase, AddNewClientUseCase, UpdateClientUseCase {

    private final ClientRepository clientRepository;

    @Override
    public void delete(Long id) {
        clientRepository.delete(id);
    }

    @Override
    public Client create(String firstname, String lastname) {
        return clientRepository.save(new Client(firstname, lastname));
    }

    @Override
    public Client update(long id, String firstname, String lastname) throws MissingClientException {
        Client client = clientRepository.get(id);

        client.setFirstname(firstname);
        client.setLastname(lastname);

        return clientRepository.save(client);
    }
}
