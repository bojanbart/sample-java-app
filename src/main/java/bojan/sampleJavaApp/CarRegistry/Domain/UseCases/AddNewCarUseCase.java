package bojan.sampleJavaApp.CarRegistry.Domain.UseCases;

import bojan.sampleJavaApp.CarRegistry.Domain.Entities.CarEntity;

public interface AddNewCarUseCase {
    public CarEntity create(String model, String Brand);
}
