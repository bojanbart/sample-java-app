package bojan.sampleJavaApp.CarRegistry.Adapters.RestApi.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.UUID;

@Data
public class NewRegistration {

    @NotEmpty(message = "Registration number is required.")
    @Size(min = 3, max = 10, message = "The length of registration number must be between 3 and 10 characters.")
    private String number;

    public UUID getCarId() {
        return UUID.fromString(carId);
    }

    @NotEmpty(message = "Car identifier is required.")
    @Size(min = 32, message = "The length of car identifier must be greater than 32 characters.")
    private String carId;
}
