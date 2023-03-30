package bojan.sampleJavaApp.CarRegistry.Adapters.Database.Repositories;

import bojan.sampleJavaApp.CarRegistry.Domain.Entities.CarEntity;
import bojan.sampleJavaApp.CarRegistry.Domain.Exceptions.MissingCarException;
import bojan.sampleJavaApp.CarRegistry.Domain.Repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.NonNull;

@Service
public class DatabaseCarRepository implements CarRepository {

    @Autowired
    private bojan.sampleJavaApp.CarRegistry.Adapters.Database.Repositories.JpaData.CarRepository jpaDataRepository;

    @Override
    public CarEntity save(CarEntity carEntity) {
        return jpaDataRepository.save(carEntity);
    }

    @Override
    public @NonNull CarEntity get(long id) throws MissingCarException {
        return jpaDataRepository.findById(id).orElseThrow(MissingCarException::new);
    }
}
