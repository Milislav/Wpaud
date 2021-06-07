package mk.ukim.finki.wpaud.model.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ShoppingCartnotFound  extends RuntimeException{
    public ShoppingCartnotFound(Long id) {

        super(String.format("Cart with id %d not found",id));
    }
}
