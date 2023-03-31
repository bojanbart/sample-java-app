package bojan.sampleJavaApp.CarRegistry.Adapters.Database.DataTransformers;

import bojan.sampleJavaApp.CarRegistry.Domain.Entities.RegistrationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegistrationDataTransformer {

    @Autowired
    private CarDataTransformer carDataTransformer;

    @Autowired
    private ClientDataTransformer clientDataTransformer;

    public RegistrationEntity toDomain(bojan.sampleJavaApp.CarRegistry.Adapters.Database.Entities.RegistrationEntity dbRegistration) {
        return new RegistrationEntity(
                dbRegistration.getNumber(),
                dbRegistration.getFrom(),
                dbRegistration.getTo(),
                carDataTransformer.toDomain(dbRegistration.getCarEntity()),
                clientDataTransformer.toDomain(dbRegistration.getClientEntity())
        );
    }

    public bojan.sampleJavaApp.CarRegistry.Adapters.Database.Entities.RegistrationEntity fromDomain(RegistrationEntity domainRegistration) {
        return new bojan.sampleJavaApp.CarRegistry.Adapters.Database.Entities.RegistrationEntity(
                domainRegistration.getNumber(),
                domainRegistration.getFrom(),
                domainRegistration.getTo(),
                carDataTransformer.fromDomain(domainRegistration.getCarEntity()),
                clientDataTransformer.fromDomain(domainRegistration.getClientEntity())
        );
    }
}
