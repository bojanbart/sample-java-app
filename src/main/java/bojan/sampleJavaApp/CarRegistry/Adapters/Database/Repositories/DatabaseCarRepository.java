package bojan.sampleJavaApp.CarRegistry.Adapters.Database.Repositories;

import bojan.sampleJavaApp.CarRegistry.Domain.Entities.Car;
import bojan.sampleJavaApp.CarRegistry.Domain.Repositories.CarRepository;
import org.springframework.stereotype.Service;

@Service
public class DatabaseCarRepository implements CarRepository {
    @Override
    public Car save(Car car) {
        car.setId(1L);

        return car;
    }

    @Override
    public Car get(long id) {
        return null;
    }
}
