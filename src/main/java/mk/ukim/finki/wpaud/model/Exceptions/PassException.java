package mk.ukim.finki.wpaud.model.Exceptions;

public class PassException extends RuntimeException{
    public PassException() {
        super("Password does not match");
    }
}
