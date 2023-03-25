package bojan.sampleJavaApp.CarRegistry.Domain.Entities;

import bojan.sampleJavaApp.CarRegistry.Domain.Exceptions.InvalidRegistrationTimestampException;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RegistrationTest {

    @Test
    public void setInvalidFrom() {
        // given
        Registration registrationUnderTest = new Registration("DW7K134", LocalDateTime.of(2023, 3, 29, 12, 0, 4), LocalDateTime.of(2023, 3, 30, 12, 0, 4), null, null);

        // when & then
        Throwable exception = assertThrows(InvalidRegistrationTimestampException.class, () -> registrationUnderTest.setFrom(LocalDateTime.of(2023, 4, 29, 12, 0, 4)));
        assertEquals("Registration from cannot be greater than to", exception.getMessage());
    }

    @Test
    public void setValidFrom() throws InvalidRegistrationTimestampException {
        // given
        Registration registrationUnderTest = new Registration("DW7K134", LocalDateTime.of(2023, 3, 29, 12, 0, 4), LocalDateTime.of(2023, 3, 30, 12, 0, 4), null, null);

        // when
        registrationUnderTest.setFrom(LocalDateTime.of(2023, 2, 25, 12, 0, 4));

        // then
        assertEquals(LocalDateTime.of(2023, 2, 25, 12, 0, 4), registrationUnderTest.getFrom());
    }

    @Test
    public void setInvalidTo() {
        // given
        Registration registrationUnderTest = new Registration("DW7K134", LocalDateTime.of(2023, 3, 29, 12, 0, 4), null, null);

        // when & then
        Throwable exception = assertThrows(InvalidRegistrationTimestampException.class, () -> registrationUnderTest.setTo(LocalDateTime.of(2023, 2, 21, 12, 0, 4)));
        assertEquals("Registration to cannot be lesser than from", exception.getMessage());
    }

    @Test
    public void setToForEmptyFrom() {
        // given
        Registration registrationUnderTest = new Registration();

        // when & then
        Throwable exception = assertThrows(InvalidRegistrationTimestampException.class, () -> registrationUnderTest.setTo(LocalDateTime.of(2023, 4, 29, 12, 0, 4)));
        assertEquals("Provide registration from timestamp first", exception.getMessage());
    }

    @Test
    public void setValidTo() throws InvalidRegistrationTimestampException {
        // given
        Registration registrationUnderTest = new Registration("DW7K134", LocalDateTime.of(2023, 3, 29, 12, 0, 4), null, null);

        // when
        registrationUnderTest.setTo(LocalDateTime.of(2023, 3, 30, 12, 0, 4));

        // then
        assertEquals(LocalDateTime.of(2023, 3, 30, 12, 0, 4), registrationUnderTest.getTo());
    }
}
