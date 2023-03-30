package bojan.sampleJavaApp.CarRegistry.Domain.UseCases;

import bojan.sampleJavaApp.CarRegistry.Domain.Entities.ClientEntity;

import java.util.List;

public interface GetClientCollectionUseCase {

    List<ClientEntity> getClients(int pageNumber);
}
