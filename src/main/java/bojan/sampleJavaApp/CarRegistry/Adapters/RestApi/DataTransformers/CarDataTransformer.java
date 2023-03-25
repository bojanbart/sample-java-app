package bojan.sampleJavaApp.CarRegistry.Adapters.RestApi.DataTransformers;

import bojan.sampleJavaApp.CarRegistry.Adapters.RestApi.ValueObjects.CarValueObject;
import bojan.sampleJavaApp.CarRegistry.Domain.Entities.Car;

public class CarDataTransformer {

    public CarValueObject transform(Car car){
        return new CarValueObject(car.getId(), car.getModel(), car.getBrand());
    }
}
