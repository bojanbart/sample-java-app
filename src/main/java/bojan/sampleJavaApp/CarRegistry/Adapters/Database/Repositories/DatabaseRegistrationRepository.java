package bojan.sampleJavaApp.CarRegistry.Adapters.Database.Repositories;

import bojan.sampleJavaApp.CarRegistry.Domain.Entities.RegistrationEntity;
import bojan.sampleJavaApp.CarRegistry.Domain.Exceptions.MissingRegistrationException;
import bojan.sampleJavaApp.CarRegistry.Domain.Repositories.RegistrationRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class DatabaseRegistrationRepository implements RegistrationRepository {

    private final bojan.sampleJavaApp.CarRegistry.Adapters.Database.Repositories.JpaData.RegistrationRepository jpaDataRepository;


    @Override
    public RegistrationEntity save(RegistrationEntity registrationEntity) {
        return jpaDataRepository.save(registrationEntity);
    }

    @Override
    public @NonNull RegistrationEntity get(String number) throws MissingRegistrationException {
        return jpaDataRepository.findById(number).orElseThrow(MissingRegistrationException::new);
    }

    @Override
    public @NonNull List<RegistrationEntity> activeRegistrationsForCar(UUID carId) {
        return jpaDataRepository.findAllByCarEntity_IdAndToIsNull(carId).stream().toList();
    }

    @Override
    public @NonNull List<RegistrationEntity> registrationsForClient(UUID clientId) {
        return jpaDataRepository.findAllByClientEntity_Id(clientId).stream().toList();
    }
}
