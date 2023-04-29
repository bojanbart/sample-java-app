package bojan.sampleJavaApp.CarRegistry.Adapters.RestApi.DTO;

import java.util.UUID;

public record Car(UUID id, String model, String brand) {
}
