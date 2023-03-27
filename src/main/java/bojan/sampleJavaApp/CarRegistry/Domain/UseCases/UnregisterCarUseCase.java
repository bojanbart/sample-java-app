package bojan.sampleJavaApp.CarRegistry.Domain.UseCases;

import bojan.sampleJavaApp.CarRegistry.Domain.Entities.Registration;
import bojan.sampleJavaApp.CarRegistry.Domain.Exceptions.*;

public interface UnregisterCarUseCase {
    Registration unregister(String number, long clientId, long carId) throws MissingCarException, MissingClientException, MissingRegistrationException, InvalidRegistrationException;
}
