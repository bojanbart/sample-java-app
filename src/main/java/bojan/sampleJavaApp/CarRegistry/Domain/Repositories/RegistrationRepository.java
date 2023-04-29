package bojan.sampleJavaApp.CarRegistry.Domain.Repositories;

import bojan.sampleJavaApp.CarRegistry.Domain.Entities.RegistrationEntity;
import bojan.sampleJavaApp.CarRegistry.Domain.Exceptions.MissingRegistrationException;
import lombok.NonNull;

import java.util.List;
import java.util.UUID;

public interface RegistrationRepository {

    RegistrationEntity save(RegistrationEntity registrationEntity);
    @NonNull RegistrationEntity get(String number) throws MissingRegistrationException;

    @NonNull List<RegistrationEntity> activeRegistrationsForCar(UUID carId);

    @NonNull List<RegistrationEntity> registrationsForClient(UUID clientId);
}
