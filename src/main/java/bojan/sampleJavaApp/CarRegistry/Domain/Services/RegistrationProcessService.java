package bojan.sampleJavaApp.CarRegistry.Domain.Services;

import bojan.sampleJavaApp.CarRegistry.Domain.Entities.CarEntity;
import bojan.sampleJavaApp.CarRegistry.Domain.Entities.ClientEntity;
import bojan.sampleJavaApp.CarRegistry.Domain.Entities.RegistrationEntity;
import bojan.sampleJavaApp.CarRegistry.Domain.Exceptions.*;
import bojan.sampleJavaApp.CarRegistry.Domain.Factory.RegistrationFactory;
import bojan.sampleJavaApp.CarRegistry.Domain.Repositories.RegistrationCarRepository;
import bojan.sampleJavaApp.CarRegistry.Domain.Repositories.RegistrationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class RegistrationProcessService {

    private final RegistrationFactory factory;

    private final RegistrationRepository registrationRepository;

    private final RegistrationCarRepository registrationCarRepository;

    public RegistrationEntity register(String number, CarEntity car, ClientEntity client) throws InvalidRegistrationException {
        if (registrationNumberIsPresent(number)) {
            throw new InvalidRegistrationException("Registration number is already taken");
        }

        if (!registrationCarRepository.activeRegistrationsForCar(car.getId()).isEmpty()) {
            throw new InvalidRegistrationException("Unable to register car for more then one client at once. Please unregister it first");
        }

        return factory.creteNew(number, car, client);
    }

    private boolean registrationNumberIsPresent(String number) {
        try {
            registrationRepository.get(number);
        } catch (MissingRegistrationException e) {
            return false;
        }

        return true;
    }

    public RegistrationEntity unregister(RegistrationEntity registration, ClientEntity client) throws InvalidRegistrationException {
        if (registration.getClientEntity().getId() != client.getId()){
            throw new InvalidRegistrationException("Registration is not made by given client");
        }

        if (registration.getTo() != null) {
            throw new InvalidRegistrationException("Car is already unregistered");
        }

        registration.unregister();

        return registration;
    }
}
