package bojan.sampleJavaApp.CarRegistry.Adapters.RestApi.Controllers;

import bojan.sampleJavaApp.CarRegistry.Adapters.RestApi.DTO.RegistrationForUpdate;
import bojan.sampleJavaApp.CarRegistry.Adapters.RestApi.DataTransformers.RegistrationDataTransformer;
import bojan.sampleJavaApp.CarRegistry.Adapters.RestApi.DTO.NewRegistration;
import bojan.sampleJavaApp.CarRegistry.Adapters.RestApi.DTO.Registration;
import bojan.sampleJavaApp.CarRegistry.Adapters.RestApi.Services.RegistrationService;
import bojan.sampleJavaApp.CarRegistry.Domain.Exceptions.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class ClientRegistrationController {

    private final RegistrationService registrationService;

    private final RegistrationDataTransformer registrationDataTransformer = new RegistrationDataTransformer();

    @PostMapping(path = "/clients/{id}/registrations", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Registration> create(@PathVariable("id") UUID clientId, @Valid @RequestBody NewRegistration registration) throws InvalidRegistrationException, MissingClientException, MissingCarException {
        return new ResponseEntity<>(registrationDataTransformer.transform(registrationService.register(registration.getNumber(), clientId, registration.getCarId())), HttpStatus.CREATED);
    }

    @PatchMapping(path = "/clients/{id}/registrations", consumes = "application/json", produces = "application/json")
    public Registration update(@PathVariable("id") UUID clientId, @RequestBody RegistrationForUpdate registration) throws InvalidRegistrationException, MissingCarException, MissingClientException, MissingRegistrationException, InvalidRegistrationTimestampException {
        return registrationDataTransformer.transform(registrationService.unregister(registration.getNumber(), clientId));
    }

    @GetMapping(path = "/clients/{id}/registrations", produces = "application/json")
    public List<Registration> getRegistrations(@PathVariable("id") UUID clientId) {
        return registrationService.getForClient(clientId).stream().map(registrationDataTransformer::transform).toList();
    }
}
