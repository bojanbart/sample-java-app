package bojan.sampleJavaApp.CarRegistry.Domain.UseCases;

import bojan.sampleJavaApp.CarRegistry.Domain.Entities.ClientEntity;
import bojan.sampleJavaApp.CarRegistry.Domain.Exceptions.MissingClientException;

import java.util.UUID;

public interface GetClientUseCase {
    public ClientEntity get(UUID id) throws MissingClientException;
}
