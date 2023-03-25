package bojan.sampleJavaApp.CarRegistry.Adapters.RestApi.Controllers;

import bojan.sampleJavaApp.CarRegistry.Adapters.RestApi.DataTransformers.CarDataTransformer;
import bojan.sampleJavaApp.CarRegistry.Adapters.RestApi.ValueObjects.CarValueObject;
import bojan.sampleJavaApp.CarRegistry.Adapters.RestApi.ValueObjects.NewCarValueObject;
import bojan.sampleJavaApp.CarRegistry.Domain.UseCases.AddNewCarUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CarController {

    private final AddNewCarUseCase addNewCarUseCase;

    private final CarDataTransformer carDataTransformer = new CarDataTransformer();

    @PostMapping(path = "/cars", consumes = "application/json", produces = "application/json")
    public CarValueObject createCar(@RequestBody NewCarValueObject car){
        return carDataTransformer.transform(addNewCarUseCase.create(car.model(), car.brand()));
    }
}
