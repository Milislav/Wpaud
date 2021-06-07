package mk.ukim.finki.wpaud.service;

import mk.ukim.finki.wpaud.model.Category;

import java.util.List;

public interface CategoryService {

    Category create(String name, String desc);

    Category update(String name, String desc);

    void delete(String name);

    List<Category> listcategories();

    List<Category> searchcategories(String searchText);
}
