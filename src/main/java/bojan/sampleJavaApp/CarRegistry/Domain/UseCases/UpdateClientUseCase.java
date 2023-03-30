package bojan.sampleJavaApp.CarRegistry.Domain.UseCases;

import bojan.sampleJavaApp.CarRegistry.Domain.Entities.ClientEntity;
import bojan.sampleJavaApp.CarRegistry.Domain.Exceptions.MissingClientException;

public interface UpdateClientUseCase {
    ClientEntity update(long id, String firstname, String lastname) throws MissingClientException;
}
