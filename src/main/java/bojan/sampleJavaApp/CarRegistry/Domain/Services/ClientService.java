package bojan.sampleJavaApp.CarRegistry.Domain.Services;

import bojan.sampleJavaApp.CarRegistry.Domain.Entities.Client;
import bojan.sampleJavaApp.CarRegistry.Domain.Repositories.ClientRepository;
import bojan.sampleJavaApp.CarRegistry.Domain.UseCases.AddNewClientUseCase;
import bojan.sampleJavaApp.CarRegistry.Domain.UseCases.DeleteClientUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientService implements DeleteClientUseCase, AddNewClientUseCase {

    private final ClientRepository clientRepository;

    @Override
    public void delete(Long id) {
        clientRepository.delete(id);
    }

    @Override
    public Client create(String firstname, String lastname) {
        return clientRepository.save(new Client(firstname, lastname));
    }
}
