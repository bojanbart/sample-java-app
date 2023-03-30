package bojan.sampleJavaApp.CarRegistry.Adapters.RestApi.DataTransformers;

import bojan.sampleJavaApp.CarRegistry.Adapters.RestApi.DTO.Client;
import bojan.sampleJavaApp.CarRegistry.Domain.Entities.ClientEntity;

public class ClientDataTransformer {

    public Client transform(ClientEntity clientEntity){
        return new Client(clientEntity.getId(), clientEntity.getFirstname(), clientEntity.getLastname());
    }
}
