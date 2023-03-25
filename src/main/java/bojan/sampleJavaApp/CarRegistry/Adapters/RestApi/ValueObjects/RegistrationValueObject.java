package bojan.sampleJavaApp.CarRegistry.Adapters.RestApi.ValueObjects;

public record RegistrationValueObject(String number, String from, String to, long carId, String carModel, String carBrand) {
}
