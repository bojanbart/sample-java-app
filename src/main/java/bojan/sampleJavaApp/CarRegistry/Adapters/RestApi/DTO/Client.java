package bojan.sampleJavaApp.CarRegistry.Adapters.RestApi.DTO;

import java.util.UUID;

public record Client(UUID id, String firstname, String lastname) {
}
