package bojan.sampleJavaApp.CarRegistry.Adapters.RestApi.Services;

import bojan.sampleJavaApp.CarRegistry.Domain.Entities.CarEntity;
import bojan.sampleJavaApp.CarRegistry.Domain.Entities.ClientEntity;
import bojan.sampleJavaApp.CarRegistry.Domain.Entities.RegistrationEntity;
import bojan.sampleJavaApp.CarRegistry.Domain.Exceptions.InvalidRegistrationException;
import bojan.sampleJavaApp.CarRegistry.Domain.Exceptions.MissingCarException;
import bojan.sampleJavaApp.CarRegistry.Domain.Exceptions.MissingClientException;
import bojan.sampleJavaApp.CarRegistry.Domain.Exceptions.MissingRegistrationException;
import bojan.sampleJavaApp.CarRegistry.Domain.Repositories.CarRepository;
import bojan.sampleJavaApp.CarRegistry.Domain.Repositories.ClientRepository;
import bojan.sampleJavaApp.CarRegistry.Domain.Repositories.RegistrationClientRepository;
import bojan.sampleJavaApp.CarRegistry.Domain.Repositories.RegistrationRepository;
import bojan.sampleJavaApp.CarRegistry.Domain.Services.RegistrationProcessService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final CarRepository carRepository;

    private final ClientRepository clientRepository;

    private final RegistrationClientRepository registrationClientRepository;

    private final RegistrationProcessService domainService;

    private final RegistrationRepository registrationRepository;

    public List<RegistrationEntity> getForClient(UUID clientId) {
        return registrationClientRepository.registrationsForClient(clientId);
    }

    public RegistrationEntity register(String number, UUID clientId, UUID carId) throws MissingClientException, MissingCarException, InvalidRegistrationException {
        CarEntity carEntity = carRepository.get(carId);
        ClientEntity clientEntity = clientRepository.get(clientId);

        RegistrationEntity registration = domainService.register(number, carEntity, clientEntity);

        return registrationRepository.save(registration);
    }

    public RegistrationEntity unregister(String number, UUID clientId) throws MissingClientException, MissingRegistrationException, InvalidRegistrationException {
        ClientEntity clientEntity = clientRepository.get(clientId);
        RegistrationEntity registrationEntity = registrationRepository.get(number);

        return registrationRepository.save(domainService.unregister(registrationEntity, clientEntity));
    }
}
