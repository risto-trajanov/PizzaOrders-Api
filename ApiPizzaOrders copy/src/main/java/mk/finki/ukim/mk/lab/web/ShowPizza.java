package mk.finki.ukim.mk.lab.web;


import com.sun.net.httpserver.HttpServer;
import mk.finki.ukim.mk.lab.model.Pizza;
import mk.finki.ukim.mk.lab.service.PizzaService;
import mk.finki.ukim.mk.lab.service.impl.PizzaServiceImp;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "show-pizza-servlet", urlPatterns = "/")
public class ShowPizza extends HttpServlet {

    private final PizzaService pizzaServiceImp;
    private final SpringTemplateEngine springTemplateEngine;

    public ShowPizza(PizzaService pizzaServiceImp,SpringTemplateEngine springTemplateEngine) {
        this.pizzaServiceImp = pizzaServiceImp;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext webContext = new WebContext(req, resp, req.getServletContext());
        List<Pizza> pizzaList = pizzaServiceImp.listPizzas();
        webContext.setVariable("pizzas", pizzaList);
        resp.setContentType("text/html; charset=UTF-8");
        this.springTemplateEngine.process("listPizzas.html", webContext, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String pizza = req.getParameter("pizza");
        session.setAttribute("pizzaName", pizza);
        resp.sendRedirect("/selectPizza.do");

    }
}
