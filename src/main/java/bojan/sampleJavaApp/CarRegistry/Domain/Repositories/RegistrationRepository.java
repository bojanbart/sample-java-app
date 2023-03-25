package bojan.sampleJavaApp.CarRegistry.Domain.Repositories;

import bojan.sampleJavaApp.CarRegistry.Domain.Entities.Registration;
import java.util.List;

public interface RegistrationRepository {

    Registration save(Registration registration);
    Registration get(String number);

    List<Registration> activeRegistrationsForCar(long carId);

    List<Registration> registrationsForClient(long clientId);
}
