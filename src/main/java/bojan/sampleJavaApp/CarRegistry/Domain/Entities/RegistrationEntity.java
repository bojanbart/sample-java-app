package bojan.sampleJavaApp.CarRegistry.Domain.Entities;

import bojan.sampleJavaApp.CarRegistry.Domain.Exceptions.InvalidRegistrationTimestampException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class RegistrationEntity {

    public RegistrationEntity(String number, LocalDateTime from, CarEntity carEntity, ClientEntity clientEntity) {
        this.number = number;
        this.from = from;
        this.carEntity = carEntity;
        this.clientEntity = clientEntity;
    }

    @Setter
    private String number;

    private LocalDateTime from;

    private LocalDateTime to;
    @Setter
    private CarEntity carEntity;

    @Setter
    private ClientEntity clientEntity;

    public void setFrom(LocalDateTime from) throws InvalidRegistrationTimestampException {
        if (to != null && to.isBefore(from)) {
            throw new InvalidRegistrationTimestampException("Registration from cannot be greater than to");
        }

        this.from = from;
    }

    public void setTo(LocalDateTime to) throws InvalidRegistrationTimestampException {
        if (from == null) {
            throw new InvalidRegistrationTimestampException("Provide registration from timestamp first");
        }

        if (to.isBefore(from)) {
            throw new InvalidRegistrationTimestampException("Registration to cannot be lesser than from");
        }

        this.to = to;
    }
}
