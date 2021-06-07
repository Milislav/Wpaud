package mk.ukim.finki.wpaud.web.webserlvets;


import mk.ukim.finki.wpaud.model.Exceptions.InvalidCredentialsException;
import mk.ukim.finki.wpaud.model.User;
import mk.ukim.finki.wpaud.service.AuthService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LogInServlet" , urlPatterns = "/servlet/logIn") //smeni go vo samo /logIn ako sakash da raboti
public class LogInServlet extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;
    private final AuthService authService;

    public LogInServlet(SpringTemplateEngine springTemplateEngine, AuthService authService) {
        this.springTemplateEngine = springTemplateEngine;
        this.authService = authService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req,resp,req.getServletContext());
        springTemplateEngine.process("logIn.html",context,resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User user = null;

        try {
            user = authService.LogIn(username,password);
        }catch (InvalidCredentialsException ex){
            WebContext context = new WebContext(req,resp,req.getServletContext());
            context.setVariable("hasError",true);
            context.setVariable("error",ex.getMessage());
            springTemplateEngine.process("logIn.html",context,resp.getWriter());
        }

        req.getSession().setAttribute("user",user);
        resp.sendRedirect("/servlet/thymeleaf/category");

    }
}
