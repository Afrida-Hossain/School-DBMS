package ExceptionHandling;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class ExceptionHandling {

    @ExceptionHandler(value= PersistenceException.class)
    public String DBExceptionHandling(){
        return "DBException";
    }

}
