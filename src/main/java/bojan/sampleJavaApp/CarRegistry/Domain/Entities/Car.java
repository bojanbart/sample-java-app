package bojan.sampleJavaApp.CarRegistry.Domain.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Car {

    public Car(String model, String brand) {
        this.model = model;
        this.brand = brand;
    }

    private Long id;

    private String model;

    private String brand;
}
