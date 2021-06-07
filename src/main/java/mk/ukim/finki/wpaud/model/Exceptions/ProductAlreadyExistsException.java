package mk.ukim.finki.wpaud.model.Exceptions;

public class ProductAlreadyExistsException extends RuntimeException {
    public ProductAlreadyExistsException(String name) {
        super(String.format("Product with name %s already exists" , name));
    }
}
