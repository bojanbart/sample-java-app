package bojan.sampleJavaApp.CarRegistry.Adapters.RestApi.Controllers;

import bojan.sampleJavaApp.CarRegistry.Adapters.RestApi.DataTransformers.ClientDataTransformer;
import bojan.sampleJavaApp.CarRegistry.Adapters.RestApi.DTO.Client;
import bojan.sampleJavaApp.CarRegistry.Adapters.RestApi.DTO.NewClient;
import bojan.sampleJavaApp.CarRegistry.Domain.Exceptions.MissingClientException;
import bojan.sampleJavaApp.CarRegistry.Domain.UseCases.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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
    public Client getSingleClient(@PathVariable("id") UUID id) throws MissingClientException {
        return clientDataTransformer.transform(getClientUseCase.get(id));
    }

    @GetMapping(path = "/clients", produces = "application/json")
    public List<Client> getManyClients(@RequestParam(value = "page", defaultValue = "0") String page) {
        return getClientCollectionUseCase.getClients(parseInt(page)).stream().map(clientDataTransformer::transform).toList();
    }

    @PutMapping(path = "/clients/{id}", consumes = "application/json", produces = "application/json")
    public Client updateClient(@PathVariable("id") UUID id, @Valid @RequestBody NewClient client) throws MissingClientException {
        return clientDataTransformer.transform(updateClientUseCase.update(id, client.getFirstname(), client.getLastname()));
    }

    @PostMapping(path = "/clients", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Client> createClient(@Valid @RequestBody NewClient client) {
        return new ResponseEntity<>(clientDataTransformer.transform(addNewClientUseCase.create(client.getFirstname(), client.getLastname())), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/clients/{id}")
    public void deleteClient(@PathVariable("id") UUID id) {
        deleteClientUseCase.delete(id);
    }
}
