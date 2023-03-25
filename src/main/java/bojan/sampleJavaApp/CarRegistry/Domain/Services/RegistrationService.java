package bojan.sampleJavaApp.CarRegistry.Domain.Services;

import bojan.sampleJavaApp.CarRegistry.Domain.Entities.Car;
import bojan.sampleJavaApp.CarRegistry.Domain.Entities.Client;
import bojan.sampleJavaApp.CarRegistry.Domain.Entities.Registration;
import bojan.sampleJavaApp.CarRegistry.Domain.Exceptions.*;
import bojan.sampleJavaApp.CarRegistry.Domain.Repositories.CarRepository;
import bojan.sampleJavaApp.CarRegistry.Domain.Repositories.ClientRepository;
import bojan.sampleJavaApp.CarRegistry.Domain.Repositories.RegistrationRepository;
import bojan.sampleJavaApp.CarRegistry.Domain.UseCases.GetRegistrationsForClientUseCase;
import bojan.sampleJavaApp.CarRegistry.Domain.UseCases.RegisterCarUseCase;
import bojan.sampleJavaApp.CarRegistry.Domain.UseCases.UnregisterCarUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;

@AllArgsConstructor
@Service
public class RegistrationService implements RegisterCarUseCase, UnregisterCarUseCase, GetRegistrationsForClientUseCase {

    private final CarRepository carRepository;

    private final ClientRepository clientRepository;

    private final RegistrationRepository registrationRepository;

    @Override
    public List<Registration> getForClient(long clientId) {
        return registrationRepository.registrationsForClient(clientId);
    }

    @Override
    public Registration register(String number, long clientId, long carId) throws MissingClientException, MissingCarException, InvalidRegistrationException {
        Car car = carRepository.get(carId);
        Client client = clientRepository.get(clientId);

        if (car == null) {
            throw new MissingCarException();
        }

        if (client == null) {
            throw new MissingClientException();
        }

        if (!registrationRepository.activeRegistrationsForCar(carId).isEmpty()) {
            throw new InvalidRegistrationException("Unable to register car for more then one client at once. Please unregister it first");
        }

        LocalDateTime registrationDateTime = LocalDateTime.now();
        Registration newRegistration = new Registration(number, registrationDateTime, car, client);

        return registrationRepository.save(newRegistration);
    }

    @Override
    public Registration unregister(String number, long clientId, long carId) throws InvalidRegistrationTimestampException, MissingCarException, MissingClientException, MissingRegistrationException, InvalidRegistrationException {
        Car car = carRepository.get(carId);
        Client client = clientRepository.get(clientId);

        if (car == null) {
            throw new MissingCarException();
        }

        if (client == null) {
            throw new MissingClientException();
        }

        Registration registration = registrationRepository.get(number);

        if (registration == null) {
            throw new MissingRegistrationException();
        }

        if (registration.getTo() != null) {
            throw new InvalidRegistrationException("Car is already unregistered");
        }

        LocalDateTime unregisterDateTime = LocalDateTime.now();
        registration.setTo(unregisterDateTime);

        return registrationRepository.save(registration);
    }
}
