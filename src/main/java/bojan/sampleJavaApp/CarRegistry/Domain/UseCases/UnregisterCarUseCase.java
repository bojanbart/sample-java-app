package bojan.sampleJavaApp.CarRegistry.Domain.UseCases;

import bojan.sampleJavaApp.CarRegistry.Domain.Entities.RegistrationEntity;
import bojan.sampleJavaApp.CarRegistry.Domain.Exceptions.*;

import java.util.UUID;

public interface UnregisterCarUseCase {
    RegistrationEntity unregister(String number, UUID clientId) throws MissingClientException, MissingRegistrationException, InvalidRegistrationException;
}
