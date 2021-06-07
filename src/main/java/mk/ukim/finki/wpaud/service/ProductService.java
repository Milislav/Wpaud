package mk.ukim.finki.wpaud.service;


import mk.ukim.finki.wpaud.model.Category;
import mk.ukim.finki.wpaud.model.Manufacturer;
import mk.ukim.finki.wpaud.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> findall();
    Optional<Product> findbyId(Long id);
    Optional<Product> findbyName(String name);




    Optional<Product> save(String name, Double price, Integer quantity, Long categoryID, Long manufecturerID);



    void deleteByID(Long id);
}
