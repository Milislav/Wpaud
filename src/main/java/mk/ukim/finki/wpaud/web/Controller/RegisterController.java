package mk.ukim.finki.wpaud.web.Controller;


import mk.ukim.finki.wpaud.model.Exceptions.InvalidArgumentException;
import mk.ukim.finki.wpaud.model.Exceptions.PassException;
import mk.ukim.finki.wpaud.model.Exceptions.UsernameAlreadyExists;
import mk.ukim.finki.wpaud.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private final AuthService authService;

    public RegisterController(AuthService authService){
        this.authService = authService;
    }

    @GetMapping
    public String getRegister(@RequestParam(required = false) String error, Model model){
        if(error != null && !error.isEmpty()){
            model.addAttribute("hasError" , true);
            model.addAttribute("error", error);
        }

        return "Register";
    }

    @PostMapping
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String repeatpassword,
                           @RequestParam String name ,
                           @RequestParam String surname){
        try {
            this.authService.Register(username,password,repeatpassword,name,surname);
            return "redirect:/logIn";
        }catch (InvalidArgumentException | PassException Exception ){
            return "redirect:/register?error=" + Exception.getMessage();
        }



    }

}
