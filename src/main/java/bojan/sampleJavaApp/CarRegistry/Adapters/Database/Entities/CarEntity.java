package bojan.sampleJavaApp.CarRegistry.Adapters.Database.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "car")
public class CarEntity {

    public CarEntity(String model, String brand) {
        this.model = model;
        this.brand = brand;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String model;

    private String brand;

    @OneToMany(mappedBy = "carEntity", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE })
    private List<RegistrationEntity> registrationEntities = new ArrayList<RegistrationEntity>();
}
