package mk.ukim.finki.wpaud.web.webserlvets;


import jdk.jfr.Category;
import mk.ukim.finki.wpaud.service.CategoryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "Category" , urlPatterns = "/servlet/category")
public class CategoryServlet extends HttpServlet {

    private final CategoryService categoryService;

    public CategoryServlet(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String ip = req.getRemoteAddr();
        String clientAgent = req.getHeader("User-agent");

        out.println("<html>");
        out.println("<head>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h3>User info  </h3>");
        out.format("<h3> %s    %s </h3>" , ip,clientAgent);
        out.println("<h3>");
        out.println("Category List");
        out.println("</h3>");
        out.println("<ul>");

          categoryService.listcategories().stream().forEach(category ->
                out.format("<li>%s (%s)</li>",category.getCategory() , category.getDesc()));

        out.println("</ul>");
        out.println("<h3> Add category </h3>");
        out.println("<form method='post' action='/servlet/category'>" +
                "<label for='name'>Name:</label><input id='name' type='text' name='name'/>"+
                "<label for='desc'>Description:</label><input id='desc' type='text' name='description'/>"+
                "<input type='submit' value='Submit'/>"+
                "</form>");
        out.println("</body>");
        out.println("</html>");


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ime = req.getParameter("name");
        String desc = req.getParameter("description");
        categoryService.create(ime , desc);

        resp.sendRedirect("/servlet/category");
    }
}
