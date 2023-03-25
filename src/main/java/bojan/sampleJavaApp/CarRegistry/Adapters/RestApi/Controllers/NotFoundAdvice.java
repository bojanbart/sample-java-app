package bojan.sampleJavaApp.CarRegistry.Adapters.RestApi.Controllers;

import bojan.sampleJavaApp.CarRegistry.Domain.Exceptions.MissingException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class NotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(MissingException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String notFound(Exception e){
        return e.getMessage();
    }
}
