package bojan.sampleJavaApp.CarRegistry.Domain.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClientEntity {

    public ClientEntity(String firstname, String lastname){
        this.firstname = firstname;
        this.lastname = lastname;
    }

    private Long id;

    private String firstname;

    private String lastname;
}
