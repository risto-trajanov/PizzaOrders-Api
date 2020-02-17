package mk.finki.ukim.mk.lab.web.servlets;


import mk.finki.ukim.mk.lab.model.Pizza;
import mk.finki.ukim.mk.lab.service.PizzaService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "select-pizza-servlet", urlPatterns = "/selectPizza.do")
public class SelectPizza extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;

    public SelectPizza(SpringTemplateEngine springTemplateEngine) {
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext webContext = new WebContext(req, resp, req.getServletContext());
        HttpSession session = req.getSession();
        String pizzaName = (String) session.getAttribute("pizzaName");
        webContext.setVariable("pizzaName", pizzaName);
        resp.setContentType("text/html; charset=UTF-8");
        this.springTemplateEngine.process("selectPizzaSize.html", webContext, resp.getWriter());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String size =  req.getParameter("pizza_size");
        session.setAttribute("pizzaSize", size);
        resp.sendRedirect("/PizzaOrder.do");


    }
}
