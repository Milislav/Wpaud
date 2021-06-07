package mk.ukim.finki.wpaud.service.Impl;

import mk.ukim.finki.wpaud.model.Exceptions.ProductAlreadyInShoppingCart;
import mk.ukim.finki.wpaud.model.Exceptions.ProductException;
import mk.ukim.finki.wpaud.model.Exceptions.ShoppingCartnotFound;
import mk.ukim.finki.wpaud.model.Exceptions.UserNotFoundException;
import mk.ukim.finki.wpaud.model.Product;
import mk.ukim.finki.wpaud.model.ShoppingCart;
import mk.ukim.finki.wpaud.model.User;
import mk.ukim.finki.wpaud.model.enumerations.ShoppingCartStatus;
import mk.ukim.finki.wpaud.repository.Jpa.ShoppingCartRepository;
import mk.ukim.finki.wpaud.repository.Jpa.UserRepository;
import mk.ukim.finki.wpaud.service.ProductService;
import mk.ukim.finki.wpaud.service.ShopingCartService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShoppingCartImpl implements ShopingCartService {

    private final ShoppingCartRepository ShoppingCartRepository;
    private final UserRepository UserRepository;
    private final ProductService productService;

    public ShoppingCartImpl(ShoppingCartRepository shoppingCartRepository, UserRepository userRepository, ProductService productService) {
        ShoppingCartRepository = shoppingCartRepository;
        UserRepository = userRepository;
        this.productService = productService;
    }


    @Override
    public List<Product> listAllProductsInShoppingCart(Long CartId) {


        if(this.ShoppingCartRepository.findById(CartId).isEmpty()){
            throw new ShoppingCartnotFound(CartId);
        }


        return this.ShoppingCartRepository.findById(CartId).get().getProducts();
    }

    @Override
    public ShoppingCart getActiveShoppingCart(String username) {

        User user  = this.UserRepository.findByUsername(username).
                orElseThrow(() -> new UserNotFoundException(username));

        return  this.ShoppingCartRepository.findByUserAndStatus(user,ShoppingCartStatus.CREATED).
                orElseGet(() -> {
                    ShoppingCart cart = new ShoppingCart(user);
                    return this.ShoppingCartRepository.save(cart);
                });


    }

    @Override
    public ShoppingCart addProducttoShoppingCart(String username, Long productId) {

        ShoppingCart shoppingCart = this.getActiveShoppingCart(username);
        Product product = this.productService.findbyId(productId).
                orElseThrow(() -> new ProductException(productId));
        if(shoppingCart.getProducts().stream().filter(p -> p.getID().
                equals(productId)).collect(Collectors.toList()).size() > 0)
            throw new ProductAlreadyInShoppingCart(productId,username);
        shoppingCart.getProducts().add(product);
        return  this.ShoppingCartRepository.save(shoppingCart);
    }
}
