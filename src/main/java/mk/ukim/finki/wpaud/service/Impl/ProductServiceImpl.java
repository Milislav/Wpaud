package mk.ukim.finki.wpaud.service.Impl;

import mk.ukim.finki.wpaud.model.Category;
import mk.ukim.finki.wpaud.model.Exceptions.CategoryNotFoundException;
import mk.ukim.finki.wpaud.model.Exceptions.ManufecturerNotFoundException;
import mk.ukim.finki.wpaud.model.Exceptions.ProductAlreadyExistsException;
import mk.ukim.finki.wpaud.model.Manufacturer;
import mk.ukim.finki.wpaud.model.Product;
import mk.ukim.finki.wpaud.repository.Impl.InMemoryCategory;
import mk.ukim.finki.wpaud.repository.Impl.InMemoryManufecturer;
import mk.ukim.finki.wpaud.repository.Impl.inMemoryProductRepository;
import mk.ukim.finki.wpaud.repository.Jpa.CategoryRepository;
import mk.ukim.finki.wpaud.repository.Jpa.ManufecturerRepository;
import mk.ukim.finki.wpaud.repository.Jpa.ProductRepository;
import mk.ukim.finki.wpaud.service.ProductService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository ProductRepository;
    private final CategoryRepository CategoryRepository;
    private final ManufecturerRepository ManufecturerRepository;

    public ProductServiceImpl(ProductRepository ProductRepository, CategoryRepository CategoryRepository, ManufecturerRepository ManufecturerRepository) {
        this.ProductRepository = ProductRepository;
        this.CategoryRepository = CategoryRepository;
        this.ManufecturerRepository = ManufecturerRepository;
    }


    @Override
    public List<Product> findall() {
        return this.ProductRepository.findAll();
    }

    @Override
    public Optional<Product> findbyId(Long id) {
        return this.ProductRepository.findById(id);
    }

    @Override
    public Optional<Product> findbyName(String name) {
        return this.ProductRepository.findByName(name);
    }




    @Override
    @Transactional
    public Optional<Product> save(String name, Double price, Integer quantity, Long categoryID, Long manufecturerID) {
        Category category = this.CategoryRepository.findById(categoryID).orElseThrow(() -> new CategoryNotFoundException(categoryID));

        Manufacturer manufacturer = this.ManufecturerRepository.findById(manufecturerID).orElseThrow(() -> new ManufecturerNotFoundException(manufecturerID));
        if(this.ProductRepository.findByName(name).isPresent()){
            throw new ProductAlreadyExistsException(name);
        }
        this.ProductRepository.deleteByName(name);

    return Optional.of(this.ProductRepository.saveAndFlush(new Product(name,price,quantity,category,manufacturer)));
    }

    @Override
    public void deleteByID(Long id) {
        this.ProductRepository.deleteById(id);

    }
}
