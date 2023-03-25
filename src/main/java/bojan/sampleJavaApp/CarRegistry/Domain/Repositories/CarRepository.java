package bojan.sampleJavaApp.CarRegistry.Domain.Repositories;

import bojan.sampleJavaApp.CarRegistry.Domain.Entities.Car;

public interface CarRepository {
    Car save(Car car);

    Car get(long id);
}
