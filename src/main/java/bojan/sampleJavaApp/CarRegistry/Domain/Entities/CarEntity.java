package bojan.sampleJavaApp.CarRegistry.Domain.Entities;

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
public class CarEntity {

    public CarEntity(String model, String brand) {
        this.model = model;
        this.brand = brand;
    }

    private long id;

    private String model;

    private String brand;
}
