package bojan.sampleJavaApp.CarRegistry.Adapters.RestApi.Controllers;

import bojan.sampleJavaApp.CarRegistry.Adapters.RestApi.DataTransformers.CarDataTransformer;
import bojan.sampleJavaApp.CarRegistry.Adapters.RestApi.DTO.Car;
import bojan.sampleJavaApp.CarRegistry.Adapters.RestApi.DTO.NewCar;
import bojan.sampleJavaApp.CarRegistry.Domain.UseCases.AddNewCarUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CarController {

    private final AddNewCarUseCase addNewCarUseCase;

    private final CarDataTransformer carDataTransformer = new CarDataTransformer();

    @PostMapping(path = "/cars", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Car> createCar(@Valid @RequestBody NewCar car){
        return new ResponseEntity<>(carDataTransformer.transform(addNewCarUseCase.create(car.getModel(), car.getBrand())), HttpStatus.CREATED);
    }
}
