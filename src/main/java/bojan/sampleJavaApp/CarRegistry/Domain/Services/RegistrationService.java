package bojan.sampleJavaApp.CarRegistry.Domain.Services;

import bojan.sampleJavaApp.CarRegistry.Domain.Entities.CarEntity;
import bojan.sampleJavaApp.CarRegistry.Domain.Entities.ClientEntity;
import bojan.sampleJavaApp.CarRegistry.Domain.Entities.RegistrationEntity;
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

@AllArgsConstructor
@Service
public class RegistrationService implements RegisterCarUseCase, UnregisterCarUseCase, GetRegistrationsForClientUseCase {

    private final CarRepository carRepository;

    private final ClientRepository clientRepository;

    private final RegistrationRepository registrationRepository;

    @Override
    public List<RegistrationEntity> getForClient(long clientId) {
        return registrationRepository.registrationsForClient(clientId);
    }

    @Override
    public RegistrationEntity register(String number, long clientId, long carId) throws MissingClientException, MissingCarException, InvalidRegistrationException {
        CarEntity carEntity = carRepository.get(carId);
        ClientEntity clientEntity = clientRepository.get(clientId);

        if (registrationNumberIsPresent(number)) {
            throw new InvalidRegistrationException("Registration number is already taken");
        }

        if (!registrationRepository.activeRegistrationsForCar(carId).isEmpty()) {
            throw new InvalidRegistrationException("Unable to register car for more then one client at once. Please unregister it first");
        }

        LocalDateTime registrationDateTime = LocalDateTime.now();
        RegistrationEntity newRegistrationEntity = new RegistrationEntity(number, registrationDateTime, carEntity, clientEntity);

        return registrationRepository.save(newRegistrationEntity);
    }

    private boolean registrationNumberIsPresent(String number) {
        try {
            registrationRepository.get(number);
        } catch (MissingRegistrationException e) {
            return false;
        }

        return true;
    }

    @Override
    public RegistrationEntity unregister(String number, long clientId, long carId) throws InvalidRegistrationTimestampException, MissingCarException, MissingClientException, MissingRegistrationException, InvalidRegistrationException {
        CarEntity carEntity = carRepository.get(carId);
        ClientEntity clientEntity = clientRepository.get(clientId);

        RegistrationEntity registrationEntity = registrationRepository.get(number);

        if (registrationEntity.getTo() != null) {
            throw new InvalidRegistrationException("Car is already unregistered");
        }

        LocalDateTime unregisterDateTime = LocalDateTime.now();
        registrationEntity.setTo(unregisterDateTime);

        return registrationRepository.save(registrationEntity);
    }
}
