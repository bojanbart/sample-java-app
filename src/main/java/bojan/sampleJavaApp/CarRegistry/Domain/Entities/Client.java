package bojan.sampleJavaApp.CarRegistry.Domain.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Client {

    public Client(String firstname, String lastname){
        this.firstname = firstname;
        this.lastname = lastname;
    }

    private Long id;

    private String firstname;

    private String lastname;
}
