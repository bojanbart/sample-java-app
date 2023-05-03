package bojan.sampleJavaApp.CarRegistry.Domain.Factory;

import bojan.sampleJavaApp.CarRegistry.Domain.Entities.CarEntity;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@NoArgsConstructor
@Component
public class CarFactory {

    public CarEntity createNew(String model, String brand) {
        return new CarEntity(UUID.randomUUID(), model, brand);
    }
}

