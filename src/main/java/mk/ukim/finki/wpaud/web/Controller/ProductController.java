package mk.ukim.finki.wpaud.web.Controller;


import mk.ukim.finki.wpaud.model.Category;
import mk.ukim.finki.wpaud.model.Manufacturer;
import mk.ukim.finki.wpaud.model.Product;
import mk.ukim.finki.wpaud.service.CategoryService;
import mk.ukim.finki.wpaud.service.ManufecturerService;
import mk.ukim.finki.wpaud.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final ManufecturerService manufecturerService;

    public ProductController(ProductService productService, CategoryService categoryService, ManufecturerService manufecturerService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.manufecturerService = manufecturerService;
    }


    @GetMapping
    public String getProductPage(@RequestParam(required = false) String error, Model model) {

        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError",true);
            model.addAttribute("error",error);

        }


    List<Product> products = productService.findall();
            model.addAttribute("products",products);
            return"products";

}

    @DeleteMapping("/delete/{id}")
    public String deleteProductPage(@PathVariable Long id){
        this.productService.deleteByID(id);
        return "redirect:/products";

    }


    @GetMapping("/add-form")
    public String addProductPage(Model model){
        List<Category> categories = this.categoryService.listcategories();
        List<Manufacturer> manufacturers = this.manufecturerService.findall();
        model.addAttribute("categories",categories);
        model.addAttribute("manufecturer",manufacturers);
        return "add-product";
    }



    @GetMapping("/edit-form/{id}")
    public String editProductPage(@PathVariable Long id, Model model){
        if(this.productService.findbyId(id).isPresent()) {

            Product product = this.productService.findbyId(id).get();
            List<Category> categories = this.categoryService.listcategories();
            List<Manufacturer> manufacturers = this.manufecturerService.findall();
            model.addAttribute("categories", categories);
            model.addAttribute("manufecturer", manufacturers);
            model.addAttribute("product",product);
          //  this.productService.deleteByID(product.getID());
            return "add-product";
        }
        return "redirect:/products?error=ProductsNotFound";
    }

    @PostMapping("/add")
    public String saveProduct(@RequestParam String name,
                              @RequestParam Double price,
                              @RequestParam Integer quantity,
                              @RequestParam Long category,
                              @RequestParam Long manufecturer){
        this.productService.save(name,price,quantity,category,manufecturer);
        return "redirect:/products";

    }


}
