package bojan.sampleJavaApp.CarRegistry.Domain.UseCases;

import bojan.sampleJavaApp.CarRegistry.Domain.Entities.ClientEntity;

public interface AddNewClientUseCase {
    public ClientEntity create(String firstname, String lastname);
}
