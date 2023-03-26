package bojan.sampleJavaApp.CarRegistry.Adapters.Database.Repositories.JpaData;

import bojan.sampleJavaApp.CarRegistry.Domain.Entities.Registration;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RegistrationRepository extends CrudRepository<Registration, String> {

    List<Registration> findAllByClient_Id(long clientId);

    List<Registration> findAllByCar_IdAndToIsNull(long carId);
}
