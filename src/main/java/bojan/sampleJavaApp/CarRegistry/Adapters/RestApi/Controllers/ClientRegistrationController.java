package bojan.sampleJavaApp.CarRegistry.Adapters.RestApi.Controllers;

import bojan.sampleJavaApp.CarRegistry.Adapters.RestApi.DataTransformers.RegistrationDataTransformer;
import bojan.sampleJavaApp.CarRegistry.Adapters.RestApi.ValueObjects.NewRegistrationValueObject;
import bojan.sampleJavaApp.CarRegistry.Adapters.RestApi.ValueObjects.RegistrationValueObject;
import bojan.sampleJavaApp.CarRegistry.Domain.Exceptions.*;
import bojan.sampleJavaApp.CarRegistry.Domain.UseCases.GetRegistrationsForClientUseCase;
import bojan.sampleJavaApp.CarRegistry.Domain.UseCases.RegisterCarUseCase;
import bojan.sampleJavaApp.CarRegistry.Domain.UseCases.UnregisterCarUseCase;
import lombok.RequiredArgsConstructor;
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
    public RegistrationValueObject create(@PathVariable("id") long clientId, @RequestBody NewRegistrationValueObject registration) throws InvalidRegistrationException, MissingClientException, MissingCarException {
        return registrationDataTransformer.transform(registerCarUseCase.register(registration.number(), clientId, registration.carId()));
    }

    @PatchMapping(path = "/clients/{id}/registrations", consumes = "application/json", produces = "application/json")
    public RegistrationValueObject update(@PathVariable("id") long clientId, @RequestBody RegistrationValueObject registration) throws InvalidRegistrationException, MissingCarException, MissingClientException, MissingRegistrationException, InvalidRegistrationTimestampException {
        return registrationDataTransformer.transform(unregisterCarUseCase.unregister(registration.number(), clientId, registration.carId()));
    }

    @GetMapping(path = "/clients/{id}/registrations", produces = "application/json")
    public List<RegistrationValueObject> getRegistrations(@PathVariable("id") long clientId) {
        return getRegistrationsForClientUseCase.getForClient(clientId).stream().map(registrationDataTransformer::transform).toList();
    }
}
