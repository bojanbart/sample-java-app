package bojan.sampleJavaApp.CarRegistry.Domain.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "client")
public class ClientEntity {

    public ClientEntity(UUID id, String firstname, String lastname) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    @Id
    @Column(name = "id", length = 16, unique = true, nullable = false)
    private UUID id;

    private String firstname;

    private String lastname;

    public void update(String firstname, String lastname){
        this.firstname = firstname;
        this.lastname = lastname;
    }

    @OneToMany(mappedBy = "clientEntity", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private List<RegistrationEntity> registrationEntities = new ArrayList<RegistrationEntity>();
}
