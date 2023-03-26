package bojan.sampleJavaApp.CarRegistry.Domain.Repositories;

import bojan.sampleJavaApp.CarRegistry.Domain.Entities.Car;
import bojan.sampleJavaApp.CarRegistry.Domain.Exceptions.MissingCarException;
import lombok.NonNull;

public interface CarRepository {
    Car save(Car car);

    @NonNull Car get(long id) throws MissingCarException;
}
