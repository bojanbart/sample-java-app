package bojan.sampleJavaApp.CarRegistry.Adapters.RestApi.DataTransformers;

import bojan.sampleJavaApp.CarRegistry.Adapters.RestApi.ValueObjects.ClientValueObject;
import bojan.sampleJavaApp.CarRegistry.Domain.Entities.Client;

public class ClientDataTransformer {

    public ClientValueObject transform(Client client){
        return new ClientValueObject(client.getId(), client.getFirstname(), client.getLastname());
    }
}
