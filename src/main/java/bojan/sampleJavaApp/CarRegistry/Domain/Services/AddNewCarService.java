package bojan.sampleJavaApp.CarRegistry.Domain.Services;

import bojan.sampleJavaApp.CarRegistry.Domain.Entities.CarEntity;
import bojan.sampleJavaApp.CarRegistry.Domain.Repositories.CarRepository;
import bojan.sampleJavaApp.CarRegistry.Domain.UseCases.AddNewCarUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddNewCarService implements AddNewCarUseCase {
    private final CarRepository carRepository;

    public CarEntity create(String model, String brand) {
        return carRepository.save(new CarEntity(model, brand));
    }
}
