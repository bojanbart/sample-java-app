package bojan.sampleJavaApp.CarRegistry.Adapters.Database.Repositories.JpaData;

import bojan.sampleJavaApp.CarRegistry.Domain.Entities.Registration;
import org.springframework.data.repository.CrudRepository;

public interface RegistrationRepository extends CrudRepository<Registration, String> {
}
