package bojan.sampleJavaApp.CarRegistry.Adapters.Database.Repositories;

import bojan.sampleJavaApp.CarRegistry.Adapters.Database.DataTransformers.CarDataTransformer;
import bojan.sampleJavaApp.CarRegistry.Domain.Entities.CarEntity;
import bojan.sampleJavaApp.CarRegistry.Domain.Exceptions.MissingCarException;
import bojan.sampleJavaApp.CarRegistry.Domain.Repositories.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import lombok.NonNull;

@Service
@AllArgsConstructor
public class DatabaseCarRepository implements CarRepository {

    private final bojan.sampleJavaApp.CarRegistry.Adapters.Database.Repositories.JpaData.CarRepository jpaDataRepository;

    private final CarDataTransformer dataTransformer;

    @Override
    public CarEntity save(CarEntity carEntity) {
        return dataTransformer.toDomain(jpaDataRepository.save(dataTransformer.fromDomain(carEntity)));
    }

    @Override
    public @NonNull CarEntity get(long id) throws MissingCarException {
        bojan.sampleJavaApp.CarRegistry.Adapters.Database.Entities.CarEntity car = jpaDataRepository.findById(id).orElseThrow(MissingCarException::new);

        return dataTransformer.toDomain(car);
    }
}
