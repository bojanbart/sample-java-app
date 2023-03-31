package bojan.sampleJavaApp.CarRegistry.Adapters.RestApi.DTO;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Data
public class NewCar {

    @NotEmpty(message = "Model is required.")
    private String model;

    @NotEmpty(message = "Brand is required.")
    private String brand;
}
