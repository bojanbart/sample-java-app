package bojan.sampleJavaApp.CarRegistry.Domain.Entities;

import bojan.sampleJavaApp.CarRegistry.Domain.Exceptions.InvalidRegistrationTimestampException;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RegistrationEntityTest {

    @Test
    public void setInvalidFrom() {
        // given
        RegistrationEntity registrationEntityUnderTest = new RegistrationEntity("DW7K134", LocalDateTime.of(2023, 3, 29, 12, 0, 4), LocalDateTime.of(2023, 3, 30, 12, 0, 4), null, null);

        // when & then
        Throwable exception = assertThrows(InvalidRegistrationTimestampException.class, () -> registrationEntityUnderTest.setFrom(LocalDateTime.of(2023, 4, 29, 12, 0, 4)));
        assertEquals("Registration from cannot be greater than to", exception.getMessage());
    }

    @Test
    public void setValidFrom() throws InvalidRegistrationTimestampException {
        // given
        RegistrationEntity registrationEntityUnderTest = new RegistrationEntity("DW7K134", LocalDateTime.of(2023, 3, 29, 12, 0, 4), LocalDateTime.of(2023, 3, 30, 12, 0, 4), null, null);

        // when
        registrationEntityUnderTest.setFrom(LocalDateTime.of(2023, 2, 25, 12, 0, 4));

        // then
        assertEquals(LocalDateTime.of(2023, 2, 25, 12, 0, 4), registrationEntityUnderTest.getFrom());
    }

    @Test
    public void setInvalidTo() {
        // given
        RegistrationEntity registrationEntityUnderTest = new RegistrationEntity("DW7K134", LocalDateTime.of(2023, 3, 29, 12, 0, 4), null, null);

        // when & then
        Throwable exception = assertThrows(InvalidRegistrationTimestampException.class, () -> registrationEntityUnderTest.setTo(LocalDateTime.of(2023, 2, 21, 12, 0, 4)));
        assertEquals("Registration to cannot be lesser than from", exception.getMessage());
    }

    @Test
    public void setToForEmptyFrom() {
        // given
        RegistrationEntity registrationEntityUnderTest = new RegistrationEntity();

        // when & then
        Throwable exception = assertThrows(InvalidRegistrationTimestampException.class, () -> registrationEntityUnderTest.setTo(LocalDateTime.of(2023, 4, 29, 12, 0, 4)));
        assertEquals("Provide registration from timestamp first", exception.getMessage());
    }

    @Test
    public void setValidTo() throws InvalidRegistrationTimestampException {
        // given
        RegistrationEntity registrationEntityUnderTest = new RegistrationEntity("DW7K134", LocalDateTime.of(2023, 3, 29, 12, 0, 4), null, null);

        // when
        registrationEntityUnderTest.setTo(LocalDateTime.of(2023, 3, 30, 12, 0, 4));

        // then
        assertEquals(LocalDateTime.of(2023, 3, 30, 12, 0, 4), registrationEntityUnderTest.getTo());
    }
}
