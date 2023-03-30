package bojan.sampleJavaApp.CarRegistry.Adapters.RestApi.DataTransformers;

import bojan.sampleJavaApp.CarRegistry.Adapters.RestApi.DTO.Registration;
import bojan.sampleJavaApp.CarRegistry.Domain.Entities.RegistrationEntity;

import java.time.format.DateTimeFormatter;

public class RegistrationDataTransformer {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public Registration transform(RegistrationEntity registrationEntity) {
        String from = registrationEntity.getFrom().format(formatter);
        String to = registrationEntity.getTo() != null ? registrationEntity.getTo().format(formatter) : "";

        return new Registration(
                registrationEntity.getNumber(),
                from,
                to,
                registrationEntity.getCarEntity().getId(),
                registrationEntity.getCarEntity().getModel(),
                registrationEntity.getCarEntity().getBrand()
        );
    }
}
