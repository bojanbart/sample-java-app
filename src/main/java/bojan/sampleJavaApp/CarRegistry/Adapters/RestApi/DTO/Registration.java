package bojan.sampleJavaApp.CarRegistry.Adapters.RestApi.DTO;

import java.util.UUID;

public record Registration(String number, String from, String to, UUID carId, String carModel, String carBrand) {
}
