package mk.ukim.finki.wpaud.service.Impl;

import mk.ukim.finki.wpaud.model.Category;
import mk.ukim.finki.wpaud.repository.Impl.InMemoryCategory;
import mk.ukim.finki.wpaud.repository.Jpa.CategoryRepository;
import mk.ukim.finki.wpaud.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository CategoryRepository;

    public CategoryServiceImpl(CategoryRepository CategoryRepository){
        this.CategoryRepository = CategoryRepository;
    }

    @Override
    public Category create(String name, String desc) {
        if(name == null || name.isEmpty()){
            throw new IllegalArgumentException();
        }
        Category c = new Category(name,desc);
        CategoryRepository.save(c);
        return c;
    }

    @Override
    public Category update(String name, String desc) {
        if(name == null || name.isEmpty()){
            throw new IllegalArgumentException();
        }
        Category c = new Category(name,desc);
        CategoryRepository.save(c);
        return c;
    }

    @Override
    public void delete(String name) {
        if(name == null || name.isEmpty()){
            throw new IllegalArgumentException();
        }
        CategoryRepository.deleteByName(name);

    }

    @Override
    public List<Category> listcategories() {
        return CategoryRepository.findAll();
    }

    @Override
    public List<Category> searchcategories(String searchText) {
        if(searchText == null || searchText.isEmpty()){
            throw  new IllegalArgumentException();
        }
        return CategoryRepository.findAllByNameLike(searchText);
    }
}
