package bojan.sampleJavaApp.CarRegistry.Adapters.Database.Repositories;

import bojan.sampleJavaApp.CarRegistry.Domain.Entities.Client;
import org.springframework.stereotype.Service;

@Service
public class DatabaseClientRepository implements bojan.sampleJavaApp.CarRegistry.Domain.Repositories.ClientRepository {
    @Override
    public Client save(Client client) {
        client.setId(4L);

        return client;
    }

    @Override
    public void delete(long clientId) {

    }

    @Override
    public Client get(long id) {
        return null;
    }
}
