package bojan.sampleJavaApp.CarRegistry.Domain.Repositories;

import bojan.sampleJavaApp.CarRegistry.Domain.Entities.CarEntity;
import bojan.sampleJavaApp.CarRegistry.Domain.Exceptions.MissingCarException;
import lombok.NonNull;

public interface CarRepository {
    CarEntity save(CarEntity carEntity);

    @NonNull CarEntity get(long id) throws MissingCarException;
}
