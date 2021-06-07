package mk.ukim.finki.wpaud.service.Impl;

import mk.ukim.finki.wpaud.model.Exceptions.InvalidArgumentException;
import mk.ukim.finki.wpaud.model.Exceptions.InvalidCredentialsException;
import mk.ukim.finki.wpaud.model.Exceptions.PassException;
import mk.ukim.finki.wpaud.model.Exceptions.UsernameAlreadyExists;
import mk.ukim.finki.wpaud.model.User;
import mk.ukim.finki.wpaud.repository.Impl.InMemoryUser;
import mk.ukim.finki.wpaud.repository.Jpa.UserRepository;
import mk.ukim.finki.wpaud.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;


    public AuthServiceImpl( UserRepository userRepository) {
        this.userRepository = userRepository;

    }


    @Override
    public User LogIn(String username, String password) {
        if(username == null || username.isEmpty() || password.isEmpty() || password == null){
            throw new InvalidArgumentException();
        }

        return this.userRepository.findByUsernameAndPassword(username,password).orElseThrow(InvalidCredentialsException::new);
    }

    @Override
    public User Register(String username, String password, String repeatpass, String name, String surname) {

        if(username == null || username.isEmpty() || password.isEmpty() || password == null){
            throw new InvalidArgumentException();
        }
        if(!password.equals(repeatpass)){
            throw  new PassException();
        }

        if(this.userRepository.findByUsername(username).isPresent()){
            throw new UsernameAlreadyExists();
        }

        User user = new User(username,password,name,surname);
        return this.userRepository.save(user);

    }
}
