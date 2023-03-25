package bojan.sampleJavaApp.CarRegistry.Domain.UseCases;

import bojan.sampleJavaApp.CarRegistry.Domain.Entities.Client;
import bojan.sampleJavaApp.CarRegistry.Domain.Exceptions.MissingClientException;

public interface GetClientUseCase {
    public Client get(Long id) throws MissingClientException;
}
