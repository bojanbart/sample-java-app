package bojan.sampleJavaApp.CarRegistry.Domain.UseCases;

import bojan.sampleJavaApp.CarRegistry.Domain.Entities.Registration;

import java.util.List;

public interface GetRegistrationsForClientUseCase {

    List<Registration> getForClient(long clientId);
}
