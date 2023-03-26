package bojan.sampleJavaApp.CarRegistry.Domain.Repositories;

import bojan.sampleJavaApp.CarRegistry.Domain.Entities.Client;
import bojan.sampleJavaApp.CarRegistry.Domain.Exceptions.MissingClientException;
import lombok.NonNull;

import java.util.List;

public interface ClientRepository {

    Client save(Client client);

    void delete(long clientId);

    @NonNull Client get(long id) throws MissingClientException;

    List<Client> getClients(int pageNumber, int itemsPerPage);
}
