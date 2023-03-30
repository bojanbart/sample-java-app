package bojan.sampleJavaApp.CarRegistry.Domain.UseCases;

import bojan.sampleJavaApp.CarRegistry.Domain.Entities.RegistrationEntity;

import java.util.List;

public interface GetRegistrationsForClientUseCase {

    List<RegistrationEntity> getForClient(long clientId);
}
