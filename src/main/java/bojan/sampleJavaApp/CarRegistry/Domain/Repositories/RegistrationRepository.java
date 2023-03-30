package bojan.sampleJavaApp.CarRegistry.Domain.Repositories;

import bojan.sampleJavaApp.CarRegistry.Domain.Entities.RegistrationEntity;
import bojan.sampleJavaApp.CarRegistry.Domain.Exceptions.MissingRegistrationException;
import lombok.NonNull;

import java.util.List;

public interface RegistrationRepository {

    RegistrationEntity save(RegistrationEntity registrationEntity);
    @NonNull RegistrationEntity get(String number) throws MissingRegistrationException;

    List<RegistrationEntity> activeRegistrationsForCar(long carId);

    List<RegistrationEntity> registrationsForClient(long clientId);
}
