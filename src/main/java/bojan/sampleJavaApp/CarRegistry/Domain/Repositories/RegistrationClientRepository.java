package bojan.sampleJavaApp.CarRegistry.Domain.Repositories;

import bojan.sampleJavaApp.CarRegistry.Domain.Entities.RegistrationEntity;
import lombok.NonNull;

import java.util.List;
import java.util.UUID;

public interface RegistrationClientRepository {
    @NonNull List<RegistrationEntity> registrationsForClient(UUID clientId);
}
