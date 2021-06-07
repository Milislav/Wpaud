package mk.ukim.finki.wpaud.model.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductException  extends RuntimeException{
    public ProductException(Long id) {
        super(String.format("Product with id %d not found",id));
    }
}
