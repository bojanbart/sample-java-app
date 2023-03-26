package bojan.sampleJavaApp.CarRegistry.Adapters.Database.Repositories;

import bojan.sampleJavaApp.CarRegistry.Domain.Entities.Car;
import bojan.sampleJavaApp.CarRegistry.Domain.Exceptions.MissingCarException;
import bojan.sampleJavaApp.CarRegistry.Domain.Repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DatabaseCarRepository implements CarRepository {

    @Autowired
    private bojan.sampleJavaApp.CarRegistry.Adapters.Database.Repositories.JpaData.CarRepository jpaDataRepository;

    @Override
    public Car save(Car car) {
        return jpaDataRepository.save(car);
    }

    @Override
    public Car get(long id) throws MissingCarException {
        return jpaDataRepository.findById(id).orElseThrow(MissingCarException::new);
    }
}
