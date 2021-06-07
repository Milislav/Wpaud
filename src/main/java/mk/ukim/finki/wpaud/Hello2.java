package mk.ukim.finki.wpaud;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Hello2 {

    @GetMapping("response-text")
    public String getResponse(){
        return "Hello";
    }

}
