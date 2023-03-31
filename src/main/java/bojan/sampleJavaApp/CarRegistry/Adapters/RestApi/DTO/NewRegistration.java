package bojan.sampleJavaApp.CarRegistry.Adapters.RestApi.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class NewRegistration {

    @NotEmpty(message = "Registration number is required.")
    @Size(min = 3, max = 10, message = "The length of registration number must be between 3 and 10 characters.")
    private String number;

    private long carId;
}
