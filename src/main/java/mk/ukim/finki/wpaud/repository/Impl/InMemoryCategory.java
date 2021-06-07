package mk.ukim.finki.wpaud.repository.Impl;

import mk.ukim.finki.wpaud.bootstrap.Dataholder;
import mk.ukim.finki.wpaud.model.Category;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class InMemoryCategory {

    public List<Category> FindAll(){

       return Dataholder.categories;
    }

    public Category save(Category c){

        if(c == null || c.getName().isEmpty()  ){
            return null;
        }
        Dataholder.categories.removeIf(category -> category.getName().equals(c.getName()));
        Dataholder.categories.add(c);
        return c;
    }

    public Optional<Category> findByName(String name){
        return Dataholder.categories.stream().filter(category -> category.getName().equals(name)).findFirst();
    }

    public Optional<Category> findById(Long id){
        return Dataholder.categories.stream().filter(category -> category.getId().equals(id)).findFirst();
    }

    public List<Category> search(String text){
        return Dataholder.categories.stream().filter(category -> category.getName().contains(text) || category.getDesc().contains(text)).
                collect(Collectors.toList());
    }
    public void delete(String name){
        if (name == null){
            return;
        }
        Dataholder.categories.removeIf(category -> category.getName().equals(name));
    }

}
