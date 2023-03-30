package bojan.sampleJavaApp.CarRegistry.Adapters.Database.Repositories.JpaData;

import bojan.sampleJavaApp.CarRegistry.Domain.Entities.RegistrationEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RegistrationRepository extends CrudRepository<RegistrationEntity, String> {

    List<RegistrationEntity> findAllByClientEntity_Id(long clientId);

    List<RegistrationEntity> findAllByCarEntity_IdAndToIsNull(long carId);
}
