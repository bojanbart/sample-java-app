package bojan.sampleJavaApp.CarRegistry.Adapters.Database.Repositories;

import bojan.sampleJavaApp.CarRegistry.Domain.Entities.Registration;
import bojan.sampleJavaApp.CarRegistry.Domain.Exceptions.MissingRegistrationException;
import bojan.sampleJavaApp.CarRegistry.Domain.Repositories.RegistrationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DatabaseRegistrationRepository implements RegistrationRepository {

    private final bojan.sampleJavaApp.CarRegistry.Adapters.Database.Repositories.JpaData.RegistrationRepository jpaDataRepository;

    @Override
    public Registration save(Registration registration) {
        return jpaDataRepository.save(registration);
    }

    @Override
    public Registration get(String number) throws MissingRegistrationException {
        return jpaDataRepository.findById(number).orElseThrow(MissingRegistrationException::new);
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
