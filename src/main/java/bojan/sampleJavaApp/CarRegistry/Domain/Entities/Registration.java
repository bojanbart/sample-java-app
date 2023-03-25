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
public class Registration {

    public Registration(String number, LocalDateTime from, Car car, Client client) {
        this.number = number;
        this.from = from;
        this.car = car;
        this.client = client;
    }

    @Setter
    private String number;

    private LocalDateTime from;

    private LocalDateTime to;
    @Setter
    private Car car;

    @Setter
    private Client client;

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
