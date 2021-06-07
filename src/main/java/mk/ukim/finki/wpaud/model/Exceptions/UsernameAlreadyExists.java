package mk.ukim.finki.wpaud.model.Exceptions;

public class UsernameAlreadyExists extends RuntimeException{
    public UsernameAlreadyExists() {
        super("Username already exists");
    }
}
