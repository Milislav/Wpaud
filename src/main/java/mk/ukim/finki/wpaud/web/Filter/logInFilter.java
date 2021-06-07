
package mk.ukim.finki.wpaud.web.Filter;

import mk.ukim.finki.wpaud.model.User;
import org.springframework.context.annotation.Profile;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.LogRecord;

@WebFilter

public class logInFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        User user = (User) req.getSession().getAttribute("user");
        String path = req.getServletPath();

        if(!"/logIn".equals(path) && !"/main.css".equals(path) && user == null && !"/register".equals(path)){

            resp.sendRedirect("/logIn");



        }else{
            filterChain.doFilter(servletRequest,servletResponse);
        }

    }


    @Override
    public void destroy() {

    }
}
