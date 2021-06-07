package mk.ukim.finki.wpaud.repository.Impl;

import mk.ukim.finki.wpaud.bootstrap.Dataholder;
import mk.ukim.finki.wpaud.model.Category;
import mk.ukim.finki.wpaud.model.Manufacturer;
import mk.ukim.finki.wpaud.model.Product;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class inMemoryProductRepository {

    public List<Product> findall(){
        return Dataholder.products;
    }

    public Optional<Product> findbyId(Long id){
        return Dataholder.products.stream().filter(product -> product.getID().equals(id)).
                findFirst();

    }
    public Optional<Product> findbyName(String name){
        return Dataholder.products.stream().filter(product -> product.getName().equals(name)).
                findFirst();

    }

    public Optional<Product> save(String name, Double price, Integer quantity, Category category, Manufacturer manufacturer){

        Dataholder.products.removeIf(product -> product.getName().equals(name));
        Product p = new Product(name,price,quantity,category,manufacturer);
        Dataholder.products.add(p);
        return  Optional.of(p);
    }

    public void deletebyID(Long id){
        Dataholder.products.removeIf(product -> product.getID().equals(id));
    }
}
