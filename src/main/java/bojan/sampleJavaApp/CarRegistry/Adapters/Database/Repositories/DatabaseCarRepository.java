package bojan.sampleJavaApp.CarRegistry.Adapters.Database.Repositories;

import bojan.sampleJavaApp.CarRegistry.Domain.Entities.CarEntity;
import bojan.sampleJavaApp.CarRegistry.Domain.Exceptions.MissingCarException;
import bojan.sampleJavaApp.CarRegistry.Domain.Repositories.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import lombok.NonNull;

import java.util.UUID;

@Service
@AllArgsConstructor
public class DatabaseCarRepository implements CarRepository {

    private final bojan.sampleJavaApp.CarRegistry.Adapters.Database.Repositories.JpaData.CarRepository jpaDataRepository;

    @Override
    public CarEntity save(CarEntity carEntity) {
        return jpaDataRepository.save(carEntity);
    }

    @Override
    public @NonNull CarEntity get(UUID id) throws MissingCarException {
        return jpaDataRepository.findById(id).orElseThrow(MissingCarException::new);
    }
}
