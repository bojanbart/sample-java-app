package bojan.sampleJavaApp.CarRegistry.Domain.UseCases;

import bojan.sampleJavaApp.CarRegistry.Domain.Entities.Client;

import java.util.List;

public interface GetClientCollectionUseCase {

    List<Client> getClients();
}
