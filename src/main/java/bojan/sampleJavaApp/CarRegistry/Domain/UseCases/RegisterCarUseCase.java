package bojan.sampleJavaApp.CarRegistry.Domain.UseCases;

import bojan.sampleJavaApp.CarRegistry.Domain.Entities.Registration;
import bojan.sampleJavaApp.CarRegistry.Domain.Exceptions.InvalidRegistrationException;
import bojan.sampleJavaApp.CarRegistry.Domain.Exceptions.MissingCarException;
import bojan.sampleJavaApp.CarRegistry.Domain.Exceptions.MissingClientException;

public interface RegisterCarUseCase {

    Registration register(String number, long clientId, long carId) throws MissingClientException, MissingCarException, InvalidRegistrationException;
}
