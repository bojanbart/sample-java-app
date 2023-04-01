package bojan.sampleJavaApp.CarRegistry.Adapters.Database.Entities;

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
@Setter
@Entity
@Table(name = "registration")
public class RegistrationEntity {

    public RegistrationEntity(String number, LocalDateTime from, CarEntity carEntity, ClientEntity clientEntity) {
        this.number = number;
        this.from = from;
        this.carEntity = carEntity;
        this.clientEntity = clientEntity;
    }

    @Id
    @Column(name = "registration_number")
    private String number;

    @Column(name = "registration_from")
    private LocalDateTime from;

    @Column(name = "registration_to")
    private LocalDateTime to;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name="car_id")
    private CarEntity carEntity;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name="client_id")
    private ClientEntity clientEntity;
}
