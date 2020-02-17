package mk.finki.ukim.mk.lab.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidVeggieTypeException extends Exception {
    public InvalidVeggieTypeException(String message) {
        super(message);
    }
}