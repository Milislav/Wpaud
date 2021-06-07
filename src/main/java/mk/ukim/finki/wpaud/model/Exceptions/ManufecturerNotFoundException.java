package mk.ukim.finki.wpaud.model.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ManufecturerNotFoundException extends  RuntimeException{
    public ManufecturerNotFoundException(Long id) {
        super(String.format("Manufecturer id %d has not been found", id));
    }
}
