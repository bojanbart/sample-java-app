package bojan.sampleJavaApp.CarRegistry.Adapters.RestApi.Controllers;

import bojan.sampleJavaApp.CarRegistry.Adapters.RestApi.DataTransformers.ClientDataTransformer;
import bojan.sampleJavaApp.CarRegistry.Adapters.RestApi.ValueObjects.ClientValueObject;
import bojan.sampleJavaApp.CarRegistry.Adapters.RestApi.ValueObjects.NewClientValueObject;
import bojan.sampleJavaApp.CarRegistry.Domain.Exceptions.MissingClientException;
import bojan.sampleJavaApp.CarRegistry.Domain.UseCases.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.lang.Integer.parseInt;

@RestController
@RequiredArgsConstructor
public class ClientController { // todo: add method to update client information

    private final GetClientUseCase getClientUseCase;

    private final DeleteClientUseCase deleteClientUseCase;

    private final AddNewClientUseCase addNewClientUseCase;

    private final UpdateClientUseCase updateClientUseCase;

    private final GetClientCollectionUseCase getClientCollectionUseCase;

    private final ClientDataTransformer clientDataTransformer = new ClientDataTransformer();

    @GetMapping(path = "/clients/{id}", produces = "application/json")
    public ClientValueObject getSingleClient(@PathVariable("id") long id) throws MissingClientException {
        return clientDataTransformer.transform(getClientUseCase.get(id));
    }

    @GetMapping(path = "/clients", produces = "application/json")
    public List<ClientValueObject> getManyClients(@RequestParam(value = "page", defaultValue = "0") String page) {
        return getClientCollectionUseCase.getClients(parseInt(page)).stream().map(clientDataTransformer::transform).toList();
    }

    @PutMapping(path = "/clients/{id}", consumes = "application/json", produces = "application/json")
    public ClientValueObject updateClient(@PathVariable("id") long id, @RequestBody NewClientValueObject client) throws MissingClientException {
        return clientDataTransformer.transform(updateClientUseCase.update(id, client.firstname(), client.lastname()));
    }

    @PostMapping(path = "/clients", consumes = "application/json", produces = "application/json")
    public ClientValueObject createClient(@RequestBody NewClientValueObject client) {
        return clientDataTransformer.transform(addNewClientUseCase.create(client.firstname(), client.lastname()));
    }

    @DeleteMapping(path = "/clients/{id}")
    public void deleteClient(@PathVariable("id") long id) {
        deleteClientUseCase.delete(id);
    }
}
