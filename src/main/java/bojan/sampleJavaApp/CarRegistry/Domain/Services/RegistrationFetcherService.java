package bojan.sampleJavaApp.CarRegistry.Domain.Services;

import bojan.sampleJavaApp.CarRegistry.Domain.Entities.RegistrationEntity;
import bojan.sampleJavaApp.CarRegistry.Domain.Repositories.RegistrationRepository;
import bojan.sampleJavaApp.CarRegistry.Domain.UseCases.GetRegistrationsForClientUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class RegistrationFetcherService implements GetRegistrationsForClientUseCase {

    private final RegistrationRepository registrationRepository;

    @Override
    public List<RegistrationEntity> getForClient(UUID clientId) {
        return registrationRepository.registrationsForClient(clientId);
    }
}
