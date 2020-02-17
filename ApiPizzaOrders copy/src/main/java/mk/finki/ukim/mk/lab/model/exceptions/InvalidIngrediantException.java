package mk.finki.ukim.mk.lab.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.function.Supplier;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidIngrediantException extends  RuntimeException {
    public InvalidIngrediantException(String message) {
        super(message);
    }
}
