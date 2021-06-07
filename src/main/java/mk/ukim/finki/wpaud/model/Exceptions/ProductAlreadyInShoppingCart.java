package mk.ukim.finki.wpaud.model.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
public class ProductAlreadyInShoppingCart  extends RuntimeException{
    public ProductAlreadyInShoppingCart(Long id, String username) {
        super(String.format("Product with id %d for %s alreay in shopingcart",id , username));
    }
}
