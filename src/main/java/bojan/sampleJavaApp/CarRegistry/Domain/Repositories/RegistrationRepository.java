package bojan.sampleJavaApp.CarRegistry.Domain.Repositories;

import bojan.sampleJavaApp.CarRegistry.Domain.Entities.Registration;
import bojan.sampleJavaApp.CarRegistry.Domain.Exceptions.MissingRegistrationException;
import lombok.NonNull;

import java.util.List;

public interface RegistrationRepository {

    Registration save(Registration registration);
    @NonNull Registration get(String number) throws MissingRegistrationException;

    List<Registration> activeRegistrationsForCar(long carId);

    List<Registration> registrationsForClient(long clientId);
}
