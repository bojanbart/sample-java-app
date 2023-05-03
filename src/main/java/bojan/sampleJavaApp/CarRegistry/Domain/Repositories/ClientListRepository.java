package bojan.sampleJavaApp.CarRegistry.Domain.Repositories;

import bojan.sampleJavaApp.CarRegistry.Domain.Entities.ClientEntity;

import java.util.List;

public interface ClientListRepository {
    List<ClientEntity> getClients(int pageNumber, int itemsPerPage);
}
