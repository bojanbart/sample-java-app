package bojan.sampleJavaApp.CarRegistry.Adapters.Database.Repositories.JpaData;

import bojan.sampleJavaApp.CarRegistry.Domain.Entities.RegistrationEntity;
import lombok.NonNull;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface RegistrationRepository extends CrudRepository<RegistrationEntity, String> {

    @NonNull List<RegistrationEntity> findAllByClientEntity_Id(UUID clientId);

    List<RegistrationEntity> findAllByCarEntity_IdAndToIsNull(UUID carId);
}
