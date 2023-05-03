package bojan.sampleJavaApp.CarRegistry.Domain.Factory;

import bojan.sampleJavaApp.CarRegistry.Domain.Entities.CarEntity;
import bojan.sampleJavaApp.CarRegistry.Domain.Entities.ClientEntity;
import bojan.sampleJavaApp.CarRegistry.Domain.Entities.RegistrationEntity;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@NoArgsConstructor
@Component
public class RegistrationFactory {

    public RegistrationEntity creteNew(String number, CarEntity car, ClientEntity client) {
        LocalDateTime registrationDateTime = LocalDateTime.now();
        return new RegistrationEntity(number, registrationDateTime, car, client);
    }
}
