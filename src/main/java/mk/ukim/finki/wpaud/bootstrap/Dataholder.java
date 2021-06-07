package mk.ukim.finki.wpaud.bootstrap;

import mk.ukim.finki.wpaud.model.*;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class Dataholder {

    public static List<Category> categories = new ArrayList<>();
    public static  List<User> users = new ArrayList<>();
    public static  List<Manufacturer> manufacturers = new ArrayList<>();
    public static  List<Product> products = new ArrayList<>();
    public static List<ShoppingCart> shoppingCarts = new ArrayList<>();

    /*
    @PostConstruct
    public void init(){
        categories.add(new Category("Software" , "Software desc"));
        categories.add(new Category("Cats", "Cats desc"));
        users.add(new User("Milic","1234","Milislav","Stojanoski"));
        users.add(new User("Koki","mishe","kostadin","Mishev"));


        Manufacturer m = new Manufacturer("nike","ny ny");
        manufacturers.add(m);
        manufacturers.add(new Manufacturer("adidas" , "Berlin De"));
        Category category = new Category("sport","sport");
        categories.add(category);

        products.add(new Product("Ball 1",50.5, 7, category , m));
        products.add(new Product("topka 1 ",59.5, 1, category , m));
        products.add(new Product("maus 3",119.5, 12, category , m));


    }

     */

    public enum ShoppingCartStatus {
    }
}
