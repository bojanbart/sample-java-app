package bojan.sampleJavaApp.CarRegistry.Adapters.Database.DataTransformers;

import bojan.sampleJavaApp.CarRegistry.Domain.Entities.CarEntity;
import org.springframework.stereotype.Component;

@Component
public class CarDataTransformer {

    public CarEntity toDomain(bojan.sampleJavaApp.CarRegistry.Adapters.Database.Entities.CarEntity dbCar){
        return new CarEntity(
                dbCar.getId(),
                dbCar.getModel(),
                dbCar.getBrand()
        );
    }

    public bojan.sampleJavaApp.CarRegistry.Adapters.Database.Entities.CarEntity fromDomain(CarEntity domainCar){
        return new bojan.sampleJavaApp.CarRegistry.Adapters.Database.Entities.CarEntity(
                domainCar.getId(),
                domainCar.getModel(),
                domainCar.getBrand(),
                null
        );
    }
}
