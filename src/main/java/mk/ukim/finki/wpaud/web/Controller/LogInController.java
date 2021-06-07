package mk.ukim.finki.wpaud.web.Controller;

import mk.ukim.finki.wpaud.model.Exceptions.InvalidCredentialsException;
import mk.ukim.finki.wpaud.model.User;
import mk.ukim.finki.wpaud.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.context.WebContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/logIn")
public class LogInController {

    public final AuthService authService;

    public LogInController(AuthService authService){
        this.authService = authService;
    }

    @GetMapping
    public String getLoginPage(){
        return "logIn";
    }

    @PostMapping
    public String getPost(HttpServletRequest req, Model model, HttpServletResponse resp){
        User user = null;

        try {
            user = this.authService.LogIn(req.getParameter("username"),req.getParameter("password"));
            req.getSession().setAttribute("user",user);
            return "redirect:/home";

        }catch (InvalidCredentialsException exception){
            model.addAttribute("hasError", true);
            model.addAttribute("error", exception.getMessage());
            return "logIn";

        }

    }

}
