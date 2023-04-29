package bojan.sampleJavaApp.CarRegistry.Adapters.Database.Repositories.JpaData;


import bojan.sampleJavaApp.CarRegistry.Domain.Entities.ClientEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface ClientRepository extends PagingAndSortingRepository<ClientEntity, UUID>, CrudRepository<ClientEntity, UUID> {
}
