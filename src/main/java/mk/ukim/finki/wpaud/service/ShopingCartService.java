package mk.ukim.finki.wpaud.service;

import mk.ukim.finki.wpaud.model.Product;
import mk.ukim.finki.wpaud.model.ShoppingCart;

import java.util.List;

public interface ShopingCartService {

    List<Product> listAllProductsInShoppingCart(Long CartId);
    ShoppingCart getActiveShoppingCart(String username);
    ShoppingCart addProducttoShoppingCart(String username, Long productId);


}
