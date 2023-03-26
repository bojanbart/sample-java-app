package bojan.sampleJavaApp.CarRegistry.Domain.Entities;

import bojan.sampleJavaApp.CarRegistry.Domain.Exceptions.InvalidRegistrationTimestampException;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table(name = "registration")
public class Registration {

    public Registration(String number, LocalDateTime from, Car car, Client client) {
        this.number = number;
        this.from = from;
        this.car = car;
        this.client = client;
    }

    @Setter
    @Id
    @Column(name = "registration_number")
    private String number;

    @Column(name = "registration_from")
    private LocalDateTime from;

    @Column(name = "registration_to")
    private LocalDateTime to;
    @Setter
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name="car_id")
    private Car car;

    @Setter
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name="client_id")
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
