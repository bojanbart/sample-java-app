package bojan.sampleJavaApp.CarRegistry.Adapters.Database.DataTransformers;

import bojan.sampleJavaApp.CarRegistry.Domain.Entities.ClientEntity;
import org.springframework.stereotype.Component;

@Component

public class ClientDataTransformer {

    public ClientEntity toDomain(bojan.sampleJavaApp.CarRegistry.Adapters.Database.Entities.ClientEntity dbClient){
        return new ClientEntity(
                dbClient.getId(),
                dbClient.getFirstname(),
                dbClient.getLastname()
        );
    }

    public bojan.sampleJavaApp.CarRegistry.Adapters.Database.Entities.ClientEntity fromDomain(ClientEntity domainClient){
        return new bojan.sampleJavaApp.CarRegistry.Adapters.Database.Entities.ClientEntity(
                domainClient.getId(),
                domainClient.getFirstname(),
                domainClient.getLastname(),
                null
        );
    }
}
