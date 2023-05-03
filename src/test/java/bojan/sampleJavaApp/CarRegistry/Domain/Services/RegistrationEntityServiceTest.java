package bojan.sampleJavaApp.CarRegistry.Domain.Services;

import bojan.sampleJavaApp.CarRegistry.Domain.Entities.CarEntity;
import bojan.sampleJavaApp.CarRegistry.Domain.Entities.ClientEntity;
import bojan.sampleJavaApp.CarRegistry.Domain.Entities.RegistrationEntity;
import bojan.sampleJavaApp.CarRegistry.Domain.Exceptions.InvalidRegistrationException;
import bojan.sampleJavaApp.CarRegistry.Domain.Exceptions.MissingCarException;
import bojan.sampleJavaApp.CarRegistry.Domain.Exceptions.MissingClientException;
import bojan.sampleJavaApp.CarRegistry.Domain.Exceptions.MissingRegistrationException;
import bojan.sampleJavaApp.CarRegistry.Domain.Repositories.CarRepository;
import bojan.sampleJavaApp.CarRegistry.Domain.Repositories.ClientRepository;
import bojan.sampleJavaApp.CarRegistry.Domain.Repositories.RegistrationRepository;
import lombok.NonNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class RegistrationEntityServiceTest {

    private CarRepository carRepository;
    private ClientRepository clientRepository;
    private RegistrationRepository registrationRepository;

    private RegistrationService serviceUnderTest;

    @BeforeEach
    void setUp() {
        carRepository = new CarRepository() {
            private final Map<UUID, CarEntity> data = new HashMap<>();

            @Override
            public CarEntity save(CarEntity carEntity) {
                data.put(carEntity.getId(), carEntity);

                return carEntity;
            }

            @Override
            public @NonNull CarEntity get(UUID id) throws MissingCarException {
                CarEntity carEntity = data.get(id);

                if (carEntity == null) {
                    throw new MissingCarException();
                }

                return carEntity;
            }
        };

        clientRepository = new ClientRepository() {

            private final Map<UUID, ClientEntity> data = new HashMap<>();

            @Override
            public ClientEntity save(ClientEntity clientEntity) {
                data.put(clientEntity.getId(), clientEntity);
                return clientEntity;
            }

            @Override
            public void delete(UUID clientId) {
                // pass
            }

            @Override
            public @NonNull ClientEntity get(UUID id) throws MissingClientException {
                ClientEntity clientEntity = data.get(id);

                if (clientEntity == null) {
                    throw new MissingClientException();
                }

                return clientEntity;
            }
        };

        registrationRepository = new RegistrationRepository() {

            private final Map<String, RegistrationEntity> data = new HashMap<String, RegistrationEntity>();

            @Override
            public RegistrationEntity save(RegistrationEntity registrationEntity) {
                data.put(registrationEntity.getNumber(), registrationEntity);
                return registrationEntity;
            }

            @Override
            public @NonNull RegistrationEntity get(String number) throws MissingRegistrationException {
                RegistrationEntity registrationEntity = data.get(number);

                if (registrationEntity == null) {
                    throw new MissingRegistrationException();
                }

                return registrationEntity;
            }

            @Override
            public @NonNull List<RegistrationEntity> activeRegistrationsForCar(UUID carId) {
                List<RegistrationEntity> result = new ArrayList<RegistrationEntity>();

                data.forEach((k, v) -> {
                    if (v.getTo() == null && v.getCarEntity().getId() == carId) {
                        result.add(v);
                    }
                });

                return result;
            }

            @Override
            public @NonNull List<RegistrationEntity> registrationsForClient(UUID clientId) {
                return null;
            }
        };

        serviceUnderTest = new RegistrationService(carRepository, clientRepository, registrationRepository);
    }

    @Test
    public void shouldNotRegisterSameNumberTwice() {
        // given
        CarEntity carEntity1 = new CarEntity(UUID.randomUUID(), "Nissan", "Micra");
        CarEntity carEntity2 = new CarEntity(UUID.randomUUID(), "Opel", "Astra");
        ClientEntity clientEntity1 = new ClientEntity(UUID.randomUUID(), "Czesław", "Niemen");

        carRepository.save(carEntity1);
        carRepository.save(carEntity2);
        clientRepository.save(clientEntity1);

        // when & then
        assertDoesNotThrow(() -> serviceUnderTest.register("DW7K134", clientEntity1.getId(), carEntity1.getId()));
        Throwable exception = assertThrows(InvalidRegistrationException.class, () -> serviceUnderTest.register("DW7K134", clientEntity1.getId(), carEntity2.getId()));
        assertEquals("Registration number is already taken", exception.getMessage());
    }

    @Test
    public void shouldNotRegisterSameCarTwice() {
        // given
        CarEntity carEntity1 = new CarEntity(UUID.randomUUID(), "Nissan", "Micra");
        ClientEntity clientEntity1 = new ClientEntity(UUID.randomUUID(), "Czesław", "Niemen");

        carRepository.save(carEntity1);
        clientRepository.save(clientEntity1);

        // when & then
        assertDoesNotThrow(() -> serviceUnderTest.register("WZ7K134", clientEntity1.getId(), carEntity1.getId()));
        Throwable exception = assertThrows(InvalidRegistrationException.class, () -> serviceUnderTest.register("DW7K134", clientEntity1.getId(), carEntity1.getId()));
        assertEquals("Unable to register car for more then one client at once. Please unregister it first", exception.getMessage());
    }

    @Test
    public void shouldRegisterCar() {
        // given
        CarEntity carEntity1 = new CarEntity(UUID.randomUUID(), "Nissan", "Micra");
        ClientEntity clientEntity1 = new ClientEntity(UUID.randomUUID(), "Czesław", "Niemen");

        carRepository.save(carEntity1);
        clientRepository.save(clientEntity1);

        // when & then
        assertDoesNotThrow(() -> serviceUnderTest.register("DW7K134", clientEntity1.getId(), carEntity1.getId()));
        assertDoesNotThrow(() -> registrationRepository.get("DW7K134"));
    }
}
