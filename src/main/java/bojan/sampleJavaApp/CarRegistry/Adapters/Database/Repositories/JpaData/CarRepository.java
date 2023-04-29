package bojan.sampleJavaApp.CarRegistry.Adapters.Database.Repositories.JpaData;

import bojan.sampleJavaApp.CarRegistry.Domain.Entities.CarEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CarRepository extends CrudRepository<CarEntity, UUID> {
}
