package bojan.sampleJavaApp.CarRegistry.Adapters.RestApi.DataTransformers;

import bojan.sampleJavaApp.CarRegistry.Adapters.RestApi.ValueObjects.RegistrationValueObject;
import bojan.sampleJavaApp.CarRegistry.Domain.Entities.Registration;

import java.time.format.DateTimeFormatter;

public class RegistrationDataTransformer {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public RegistrationValueObject transform(Registration registration) {
        String from = registration.getFrom().format(formatter);
        String to = registration.getTo() != null ? registration.getTo().format(formatter) : "";

        return new RegistrationValueObject(
                registration.getNumber(),
                from,
                to,
                registration.getCar().getId(),
                registration.getCar().getModel(),
                registration.getCar().getBrand()
        );
    }
}
