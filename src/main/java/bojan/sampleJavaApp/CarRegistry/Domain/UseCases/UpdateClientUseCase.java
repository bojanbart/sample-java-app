package bojan.sampleJavaApp.CarRegistry.Domain.UseCases;

import bojan.sampleJavaApp.CarRegistry.Domain.Entities.Client;
import bojan.sampleJavaApp.CarRegistry.Domain.Exceptions.MissingClientException;

public interface UpdateClientUseCase {
    Client update(long id, String firstname, String lastname) throws MissingClientException;
}
