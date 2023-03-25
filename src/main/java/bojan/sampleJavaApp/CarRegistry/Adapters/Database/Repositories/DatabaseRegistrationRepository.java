package bojan.sampleJavaApp.CarRegistry.Adapters.Database.Repositories;

import bojan.sampleJavaApp.CarRegistry.Domain.Entities.Registration;
import bojan.sampleJavaApp.CarRegistry.Domain.Repositories.RegistrationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DatabaseRegistrationRepository implements RegistrationRepository {
    @Override
    public Registration save(Registration registration) {
        return null;
    }

    @Override
    public Registration get(String number) {
        return null;
    }

    @Override
    public List<Registration> activeRegistrationsForCar(long carId) {
        return null;
    }

    @Override
    public List<Registration> registrationsForClient(long clientId) {
        return null;
    }
}
