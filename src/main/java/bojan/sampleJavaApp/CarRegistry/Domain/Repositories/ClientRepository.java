package bojan.sampleJavaApp.CarRegistry.Domain.Repositories;

import bojan.sampleJavaApp.CarRegistry.Domain.Entities.ClientEntity;
import bojan.sampleJavaApp.CarRegistry.Domain.Exceptions.MissingClientException;
import lombok.NonNull;

import java.util.UUID;

public interface ClientRepository {

    ClientEntity save(ClientEntity clientEntity);

    void delete(UUID clientId);

    @NonNull ClientEntity get(UUID id) throws MissingClientException;
}
