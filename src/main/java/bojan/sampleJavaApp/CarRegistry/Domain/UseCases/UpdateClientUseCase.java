package bojan.sampleJavaApp.CarRegistry.Domain.UseCases;

import bojan.sampleJavaApp.CarRegistry.Domain.Entities.ClientEntity;
import bojan.sampleJavaApp.CarRegistry.Domain.Exceptions.MissingClientException;

import java.util.UUID;

public interface UpdateClientUseCase {
    ClientEntity update(UUID id, String firstname, String lastname) throws MissingClientException;
}
