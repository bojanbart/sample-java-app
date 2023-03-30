package bojan.sampleJavaApp.CarRegistry.Domain.UseCases;

import bojan.sampleJavaApp.CarRegistry.Domain.Entities.RegistrationEntity;
import bojan.sampleJavaApp.CarRegistry.Domain.Exceptions.*;

public interface UnregisterCarUseCase {
    RegistrationEntity unregister(String number, long clientId, long carId) throws MissingCarException, MissingClientException, MissingRegistrationException, InvalidRegistrationException;
}
