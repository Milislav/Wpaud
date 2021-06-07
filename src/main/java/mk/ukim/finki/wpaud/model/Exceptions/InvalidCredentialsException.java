package mk.ukim.finki.wpaud.model.Exceptions;

public class InvalidCredentialsException extends RuntimeException{

    public InvalidCredentialsException() {
        super("invalid user credentials");
    }
}
