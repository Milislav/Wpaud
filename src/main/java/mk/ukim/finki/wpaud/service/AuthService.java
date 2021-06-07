package mk.ukim.finki.wpaud.service;


import mk.ukim.finki.wpaud.model.User;

public interface AuthService {

    User LogIn(String username, String password);
    User Register(String username, String password, String repeatpass,String name , String surname);
}
