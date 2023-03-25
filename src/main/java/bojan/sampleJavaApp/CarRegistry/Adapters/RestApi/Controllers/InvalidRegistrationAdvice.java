package bojan.sampleJavaApp.CarRegistry.Adapters.RestApi.Controllers;

import bojan.sampleJavaApp.CarRegistry.Domain.Exceptions.InvalidRegistrationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class InvalidRegistrationAdvice {

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidRegistrationException.class)
    String invalid(InvalidRegistrationException e){
        return e.getMessage();
    }
}
