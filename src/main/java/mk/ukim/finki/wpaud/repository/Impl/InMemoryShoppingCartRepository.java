package mk.ukim.finki.wpaud.repository.Impl;

import mk.ukim.finki.wpaud.bootstrap.Dataholder;
import mk.ukim.finki.wpaud.model.Exceptions.ShoppingCartnotFound;
import mk.ukim.finki.wpaud.model.ShoppingCart;
import mk.ukim.finki.wpaud.model.enumerations.ShoppingCartStatus;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class InMemoryShoppingCartRepository {

    public Optional<ShoppingCart> findById(Long id) {
        return Dataholder.shoppingCarts.stream().filter(i -> i.getId().equals(id)).findFirst();
    }

    public Optional<ShoppingCart> findbyUsernameandStatus(String username, ShoppingCartStatus status){
        return Dataholder.shoppingCarts.stream().filter(i ->  i.getUser().getUsername().equals(username) &&
                i.getStatus().equals(status)).findFirst();
    }

    public ShoppingCart save(ShoppingCart shoppingCart){
        Dataholder.shoppingCarts.removeIf(i -> i.getUser().
                getUsername().equals(shoppingCart.getUser().getUsername()));
        Dataholder.shoppingCarts.add(shoppingCart);
        return shoppingCart;
    }
}
