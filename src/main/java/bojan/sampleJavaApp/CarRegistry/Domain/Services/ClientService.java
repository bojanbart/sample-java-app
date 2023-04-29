package bojan.sampleJavaApp.CarRegistry.Domain.Services;

import bojan.sampleJavaApp.CarRegistry.Domain.Entities.ClientEntity;
import bojan.sampleJavaApp.CarRegistry.Domain.Exceptions.MissingClientException;
import bojan.sampleJavaApp.CarRegistry.Domain.Repositories.ClientRepository;
import bojan.sampleJavaApp.CarRegistry.Domain.UseCases.AddNewClientUseCase;
import bojan.sampleJavaApp.CarRegistry.Domain.UseCases.DeleteClientUseCase;
import bojan.sampleJavaApp.CarRegistry.Domain.UseCases.UpdateClientUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClientService implements DeleteClientUseCase, AddNewClientUseCase, UpdateClientUseCase {

    private final ClientRepository clientRepository;

    @Override
    public void delete(UUID id) {
        clientRepository.delete(id);
    }

    @Override
    public ClientEntity create(String firstname, String lastname) {
        return clientRepository.save(new ClientEntity(firstname, lastname));
    }

    @Override
    public ClientEntity update(UUID id, String firstname, String lastname) throws MissingClientException {
        ClientEntity clientEntity = clientRepository.get(id);

        clientEntity.setFirstname(firstname);
        clientEntity.setLastname(lastname);

        return clientRepository.save(clientEntity);
    }
}
