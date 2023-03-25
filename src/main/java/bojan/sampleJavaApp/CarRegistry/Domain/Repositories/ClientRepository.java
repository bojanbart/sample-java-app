package bojan.sampleJavaApp.CarRegistry.Domain.Repositories;

import bojan.sampleJavaApp.CarRegistry.Domain.Entities.Client;

public interface ClientRepository {

    Client save(Client client);

    void delete(long clientId);

    Client get(long id);
}
