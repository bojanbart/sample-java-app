package bojan.sampleJavaApp.CarRegistry.Domain.UseCases;

import bojan.sampleJavaApp.CarRegistry.Domain.Entities.Client;

public interface AddNewClientUseCase {
    public Client create(String firstname, String lastname);
}
