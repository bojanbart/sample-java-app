package bojan.sampleJavaApp.CarRegistry.Adapters.RestApi.Controllers;

import bojan.sampleJavaApp.CarRegistry.Adapters.RestApi.DataTransformers.RegistrationDataTransformer;
import bojan.sampleJavaApp.CarRegistry.Adapters.RestApi.DTO.NewRegistration;
import bojan.sampleJavaApp.CarRegistry.Adapters.RestApi.DTO.Registration;
import bojan.sampleJavaApp.CarRegistry.Domain.Exceptions.*;
import bojan.sampleJavaApp.CarRegistry.Domain.UseCases.GetRegistrationsForClientUseCase;
import bojan.sampleJavaApp.CarRegistry.Domain.UseCases.RegisterCarUseCase;
import bojan.sampleJavaApp.CarRegistry.Domain.UseCases.UnregisterCarUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ClientRegistrationController {

    private final RegisterCarUseCase registerCarUseCase;

    private final UnregisterCarUseCase unregisterCarUseCase;

    private final GetRegistrationsForClientUseCase getRegistrationsForClientUseCase;

    private final RegistrationDataTransformer registrationDataTransformer = new RegistrationDataTransformer();

    @PostMapping(path = "/clients/{id}/registrations", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Registration> create(@PathVariable("id") long clientId, @Valid @RequestBody NewRegistration registration) throws InvalidRegistrationException, MissingClientException, MissingCarException {
        return new ResponseEntity<>(registrationDataTransformer.transform(registerCarUseCase.register(registration.getNumber(), clientId, registration.getCarId())), HttpStatus.CREATED);
    }

    @PatchMapping(path = "/clients/{id}/registrations", consumes = "application/json", produces = "application/json")
    public Registration update(@PathVariable("id") long clientId, @RequestBody Registration registration) throws InvalidRegistrationException, MissingCarException, MissingClientException, MissingRegistrationException, InvalidRegistrationTimestampException {
        return registrationDataTransformer.transform(unregisterCarUseCase.unregister(registration.number(), clientId, registration.carId()));
    }

    @GetMapping(path = "/clients/{id}/registrations", produces = "application/json")
    public List<Registration> getRegistrations(@PathVariable("id") long clientId) {
        return getRegistrationsForClientUseCase.getForClient(clientId).stream().map(registrationDataTransformer::transform).toList();
    }
}
