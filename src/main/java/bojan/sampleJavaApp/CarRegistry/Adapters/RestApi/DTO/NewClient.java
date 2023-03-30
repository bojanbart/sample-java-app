package bojan.sampleJavaApp.CarRegistry.Adapters.RestApi.DTO;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Data
@Getter()
@Setter()
public class NewClient {
    @NotEmpty(message = "Firstname is required.")
    private final String firstname;

    @NotEmpty(message = "Lastname is required.")
    private final String lastname;
}
