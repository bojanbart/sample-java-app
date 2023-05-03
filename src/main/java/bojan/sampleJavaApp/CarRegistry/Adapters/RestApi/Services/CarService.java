package bojan.sampleJavaApp.CarRegistry.Adapters.RestApi.Services;

import bojan.sampleJavaApp.CarRegistry.Domain.Entities.CarEntity;
import bojan.sampleJavaApp.CarRegistry.Domain.Factory.CarFactory;
import bojan.sampleJavaApp.CarRegistry.Domain.Repositories.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CarService {

    private CarFactory factory;

    private CarRepository carRepository;

    public CarEntity createNewCar(String model, String brand) {
        CarEntity car = factory.createNew(model, brand);

        carRepository.save(car);

        return car;
    }
}
