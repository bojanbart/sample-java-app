package bojan.sampleJavaApp.CarRegistry.Domain.Exceptions;

public class InvalidRegistrationTimestampException extends InvalidRegistrationException{
    public InvalidRegistrationTimestampException(String message){
        super(message);
    }
}
