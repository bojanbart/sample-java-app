package bojan.sampleJavaApp.CarRegistry.Domain.Services;

import bojan.sampleJavaApp.CarRegistry.Domain.Entities.Car;
import bojan.sampleJavaApp.CarRegistry.Domain.Entities.Client;
import bojan.sampleJavaApp.CarRegistry.Domain.Entities.Registration;
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

public class RegistrationServiceTest {

    private CarRepository carRepository;
    private ClientRepository clientRepository;
    private RegistrationRepository registrationRepository;

    private RegistrationService serviceUnderTest;

    @BeforeEach
    void setUp() {
        carRepository = new CarRepository() {
            private final Map<Long, Car> data = new HashMap<Long, Car>();

            @Override
            public Car save(Car car) {
                data.put(car.getId(), car);

                return car;
            }

            @Override
            public @NonNull Car get(long id) throws MissingCarException {
                Car car = data.get(id);

                if (car == null) {
                    throw new MissingCarException();
                }

                return car;
            }
        };

        clientRepository = new ClientRepository() {

            private final Map<Long, Client> data = new HashMap<Long, Client>();

            @Override
            public Client save(Client client) {
                data.put(client.getId(), client);
                return client;
            }

            @Override
            public void delete(long clientId) {
                // pass
            }

            @Override
            public @NonNull Client get(long id) throws MissingClientException {
                Client client = data.get(id);

                if (client == null) {
                    throw new MissingClientException();
                }

                return client;
            }

            @Override
            public List<Client> getClients(int pageNumber, int itemsPerPage) {
                return data.values().stream().toList();
            }
        };

        registrationRepository = new RegistrationRepository() {

            private final Map<String, Registration> data = new HashMap<String, Registration>();

            @Override
            public Registration save(Registration registration) {
                data.put(registration.getNumber(), registration);
                return registration;
            }

            @Override
            public @NonNull Registration get(String number) throws MissingRegistrationException {
                Registration registration = data.get(number);

                if (registration == null) {
                    throw new MissingRegistrationException();
                }

                return registration;
            }

            @Override
            public List<Registration> activeRegistrationsForCar(long carId) {
                List<Registration> result = new ArrayList<Registration>();

                data.forEach((k, v) -> {
                    if (v.getTo() == null && v.getCar().getId() == carId) {
                        result.add(v);
                    }
                });

                return result;
            }

            @Override
            public List<Registration> registrationsForClient(long clientId) {
                return null;
            }
        };

        serviceUnderTest = new RegistrationService(carRepository, clientRepository, registrationRepository);
    }

    @Test
    public void shouldNotRegisterSameNumberTwice() {
        // given
        Car car1 = new Car(1L, "Nissan", "Micra", null);
        Car car2 = new Car(1L, "Opel", "Astra", null);
        Client client1 = new Client(1L, "Czesław", "Niemen", null);

        carRepository.save(car1);
        carRepository.save(car2);
        clientRepository.save(client1);

        // when & then
        assertDoesNotThrow(() -> serviceUnderTest.register("DW7K134", client1.getId(), car1.getId()));
        Throwable exception = assertThrows(InvalidRegistrationException.class, () -> serviceUnderTest.register("DW7K134", client1.getId(), car2.getId()));
        assertEquals("Registration number is already taken", exception.getMessage());
    }

    @Test
    public void shouldNotRegisterSameCarTwice() {
        // given
        Car car1 = new Car(1L, "Nissan", "Micra", null);
        Client client1 = new Client(1L, "Czesław", "Niemen", null);

        carRepository.save(car1);
        clientRepository.save(client1);

        // when & then
        assertDoesNotThrow(() -> serviceUnderTest.register("WZ7K134", client1.getId(), car1.getId()));
        Throwable exception = assertThrows(InvalidRegistrationException.class, () -> serviceUnderTest.register("DW7K134", client1.getId(), car1.getId()));
        assertEquals("Unable to register car for more then one client at once. Please unregister it first", exception.getMessage());
    }

    @Test
    public void shouldRegisterCar() {
        // given
        Car car1 = new Car(1L, "Nissan", "Micra", null);
        Client client1 = new Client(1L, "Czesław", "Niemen", null);

        carRepository.save(car1);
        clientRepository.save(client1);

        // when & then
        assertDoesNotThrow(() -> serviceUnderTest.register("DW7K134", client1.getId(), car1.getId()));
        assertDoesNotThrow(() -> registrationRepository.get("DW7K134"));
    }
}
