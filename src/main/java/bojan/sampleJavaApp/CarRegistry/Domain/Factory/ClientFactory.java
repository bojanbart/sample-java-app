package bojan.sampleJavaApp.CarRegistry.Domain.Factory;

import bojan.sampleJavaApp.CarRegistry.Domain.Entities.ClientEntity;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@NoArgsConstructor
@Component
public class ClientFactory {

    public ClientEntity createNew(String firstname, String lastname) {
        return new ClientEntity(UUID.randomUUID(), firstname, lastname);
    }
}
