package bojan.sampleJavaApp.CarRegistry.Adapters.Database.Repositories.JpaData;

import bojan.sampleJavaApp.CarRegistry.Adapters.Database.Entities.RegistrationEntity;
import lombok.NonNull;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RegistrationRepository extends CrudRepository<RegistrationEntity, String> {

    @NonNull List<RegistrationEntity> findAllByClientEntity_Id(long clientId);

    List<RegistrationEntity> findAllByCarEntity_IdAndToIsNull(long carId);
}
