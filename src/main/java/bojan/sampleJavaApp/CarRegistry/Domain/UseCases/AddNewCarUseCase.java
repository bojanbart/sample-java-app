package bojan.sampleJavaApp.CarRegistry.Domain.UseCases;

import bojan.sampleJavaApp.CarRegistry.Domain.Entities.Car;

public interface AddNewCarUseCase {
    public Car create(String model, String Brand);
}
