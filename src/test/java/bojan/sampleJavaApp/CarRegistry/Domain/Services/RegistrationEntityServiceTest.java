package bojan.sampleJavaApp.CarRegistry.Domain.Services;

import bojan.sampleJavaApp.CarRegistry.Domain.Entities.CarEntity;
import bojan.sampleJavaApp.CarRegistry.Domain.Entities.ClientEntity;
import bojan.sampleJavaApp.CarRegistry.Domain.Entities.RegistrationEntity;
import bojan.sampleJavaApp.CarRegistry.Domain.Exceptions.InvalidRegistrationException;
import bojan.sampleJavaApp.CarRegistry.Domain.Exceptions.MissingRegistrationException;
import bojan.sampleJavaApp.CarRegistry.Domain.Factory.RegistrationFactory;
import bojan.sampleJavaApp.CarRegistry.Domain.Repositories.RegistrationCarRepository;
import bojan.sampleJavaApp.CarRegistry.Domain.Repositories.RegistrationRepository;
import lombok.NonNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class RegistrationEntityServiceTest {

    private class RegistrationRepositoryInMemory implements  RegistrationRepository, RegistrationCarRepository {

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
    }
    private RegistrationRepositoryInMemory registrationRepository;

    private RegistrationProcessService serviceUnderTest;

    @BeforeEach
    void setUp() {

        registrationRepository = new RegistrationRepositoryInMemory();

        serviceUnderTest = new RegistrationProcessService(new RegistrationFactory(), registrationRepository, registrationRepository);
    }

    @Test
    public void shouldNotRegisterSameCarTwice() {
        // given
        CarEntity carEntity1 = new CarEntity(UUID.randomUUID(), "Nissan", "Micra");
        ClientEntity clientEntity1 = new ClientEntity(UUID.randomUUID(), "Czesław", "Niemen");

        // when & then
        assertDoesNotThrow(() -> registrationRepository.save(serviceUnderTest.register("WZ7K134", carEntity1, clientEntity1)));
        Throwable exception = assertThrows(InvalidRegistrationException.class, () -> serviceUnderTest.register("DW7K134", carEntity1, clientEntity1));
        assertEquals("Unable to register car for more then one client at once. Please unregister it first", exception.getMessage());
    }

    @Test
    public void shouldRegisterCar() {
        // given
        CarEntity carEntity1 = new CarEntity(UUID.randomUUID(), "Nissan", "Micra");
        ClientEntity clientEntity1 = new ClientEntity(UUID.randomUUID(), "Czesław", "Niemen");

        // when & then
        assertDoesNotThrow(() -> serviceUnderTest.register("DW7K134", carEntity1, clientEntity1));
    }
}
