package bojan.sampleJavaApp.CarRegistry.Domain.Services;

import bojan.sampleJavaApp.CarRegistry.Domain.Entities.ClientEntity;
import bojan.sampleJavaApp.CarRegistry.Domain.Exceptions.MissingClientException;
import bojan.sampleJavaApp.CarRegistry.Domain.Repositories.ClientRepository;
import bojan.sampleJavaApp.CarRegistry.Domain.UseCases.GetClientCollectionUseCase;
import bojan.sampleJavaApp.CarRegistry.Domain.UseCases.GetClientUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ClientFetcherService implements GetClientUseCase, GetClientCollectionUseCase {

    private final ClientRepository clientRepository;

    @Override
    public ClientEntity get(Long id) throws MissingClientException {
        return clientRepository.get(id);
    }

    @Override
    public List<ClientEntity> getClients(int pageNumber) {
        int itemsPerPage = 3;
        return clientRepository.getClients(pageNumber, itemsPerPage);
    }
}
