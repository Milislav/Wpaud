package mk.ukim.finki.wpaud.web.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hello-world")
public class HelloWorld {

    @GetMapping
    public String getHello(){
        return "/HelloWorld";
    }
}
