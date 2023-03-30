package bojan.sampleJavaApp.CarRegistry.Adapters.RestApi.DataTransformers;

import bojan.sampleJavaApp.CarRegistry.Adapters.RestApi.DTO.Car;
import bojan.sampleJavaApp.CarRegistry.Domain.Entities.CarEntity;

public class CarDataTransformer {

    public Car transform(CarEntity carEntity){
        return new Car(carEntity.getId(), carEntity.getModel(), carEntity.getBrand());
    }
}
