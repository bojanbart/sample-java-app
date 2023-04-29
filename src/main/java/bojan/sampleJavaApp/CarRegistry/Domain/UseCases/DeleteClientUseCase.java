package bojan.sampleJavaApp.CarRegistry.Domain.UseCases;

import java.util.UUID;

public interface DeleteClientUseCase {
    public void delete(UUID id);
}
