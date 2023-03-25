package bojan.sampleJavaApp.CarRegistry.Domain.Exceptions;

public class InvalidRegistrationException extends Exception {
    public InvalidRegistrationException(String message) {
        super(message);
    }
}
