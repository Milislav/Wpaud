package mk.ukim.finki.wpaud.repository.Jpa;

import mk.ukim.finki.wpaud.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {


    Optional<Product> findByName(String text);

    void deleteByName(String name);
}
