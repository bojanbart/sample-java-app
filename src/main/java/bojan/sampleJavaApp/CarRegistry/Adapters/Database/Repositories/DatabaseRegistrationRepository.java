package bojan.sampleJavaApp.CarRegistry.Adapters.Database.Repositories;

import bojan.sampleJavaApp.CarRegistry.Adapters.Database.DataTransformers.RegistrationDataTransformer;
import bojan.sampleJavaApp.CarRegistry.Domain.Entities.RegistrationEntity;
import bojan.sampleJavaApp.CarRegistry.Domain.Exceptions.MissingRegistrationException;
import bojan.sampleJavaApp.CarRegistry.Domain.Repositories.RegistrationRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DatabaseRegistrationRepository implements RegistrationRepository {

    private final bojan.sampleJavaApp.CarRegistry.Adapters.Database.Repositories.JpaData.RegistrationRepository jpaDataRepository;

    private final RegistrationDataTransformer dataTransformer;

    @Override
    public RegistrationEntity save(RegistrationEntity registrationEntity) {
        return dataTransformer.toDomain(jpaDataRepository.save(dataTransformer.fromDomain(registrationEntity)));
    }

    @Override
    public @NonNull RegistrationEntity get(String number) throws MissingRegistrationException {
        bojan.sampleJavaApp.CarRegistry.Adapters.Database.Entities.RegistrationEntity dbRegistration = jpaDataRepository.findById(number).orElseThrow(MissingRegistrationException::new);

        return dataTransformer.toDomain(dbRegistration);
    }

    @Override
    public List<RegistrationEntity> activeRegistrationsForCar(long carId) {
        return jpaDataRepository.findAllByCarEntity_IdAndToIsNull(carId).stream().map(dataTransformer::toDomain).toList();
    }

    @Override
    public List<RegistrationEntity> registrationsForClient(long clientId) {
        return jpaDataRepository.findAllByClientEntity_Id(clientId).stream().map(dataTransformer::toDomain).toList();
    }
}
