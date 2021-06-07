package mk.ukim.finki.wpaud.repository.Impl;


import mk.ukim.finki.wpaud.bootstrap.Dataholder;
import mk.ukim.finki.wpaud.model.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class InMemoryUser {

    public Optional<User> findbyusername(String username){
        return Dataholder.users.stream().filter(user -> user.getUsername().equals(username)).findFirst();
    }
    public  Optional<User> findbyUsernameandPassword(String username,String password){
        return Dataholder.users.stream().filter(user -> user.getUsername().equals(username) && user.getPassword().equals(password)).findFirst();
    }
    public  User SaveorUpdate(User u){
        Dataholder.users.removeIf(user -> user.getUsername().equals(u.getUsername()));
        Dataholder.users.add(u);
        return u;
    }
    public void delete(String username){
        Dataholder.users.removeIf(user -> user.getUsername().equals(username));

    }
}
