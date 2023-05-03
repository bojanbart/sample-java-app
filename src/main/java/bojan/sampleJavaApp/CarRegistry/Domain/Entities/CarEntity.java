package bojan.sampleJavaApp.CarRegistry.Domain.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "car")
public class CarEntity {

    public CarEntity(UUID id, String model, String brand) {
        this.id = id;
        this.model = model;
        this.brand = brand;
    }

    @Id
    @Column(name = "id", length = 16, unique = true, nullable = false)
    private UUID id;

    private String model;

    private String brand;

    @OneToMany(mappedBy = "carEntity", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE })
    private List<RegistrationEntity> registrationEntities = new ArrayList<>();
}
