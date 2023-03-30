package bojan.sampleJavaApp.CarRegistry.Domain.Repositories;

import bojan.sampleJavaApp.CarRegistry.Domain.Entities.ClientEntity;
import bojan.sampleJavaApp.CarRegistry.Domain.Exceptions.MissingClientException;
import lombok.NonNull;

import java.util.List;

public interface ClientRepository {

    ClientEntity save(ClientEntity clientEntity);

    void delete(long clientId);

    @NonNull ClientEntity get(long id) throws MissingClientException;

    List<ClientEntity> getClients(int pageNumber, int itemsPerPage);
}
