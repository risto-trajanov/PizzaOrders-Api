package mk.finki.ukim.mk.lab.web.servlets;

import mk.finki.ukim.mk.lab.service.OrderService;
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

@WebServlet(name = "order-pizza", urlPatterns = "/PizzaOrder.do")
public class PizzaOrder extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;

    public PizzaOrder(SpringTemplateEngine springTemplateEngine) {
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext webContext = new WebContext(req,resp,req.getServletContext());
        resp.setContentType("text/html; charset=UTF-8");
        HttpSession session = req.getSession();
        String pizza = (String) session.getAttribute("pizzaName");
        String size = (String) session.getAttribute("pizzaSize");
        webContext.setVariable("pizzaName", pizza);
        webContext.setVariable("pizzaSize", size);
        this.springTemplateEngine.process("deliveryInfo.html", webContext, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        /*String pizza = (String) session.getAttribute("pizza");
        String size = (String) session.getAttribute("pizza-size");
        */String client = req.getParameter("clientName");
        String clientAddress = req.getParameter("clientAddress");
        session.setAttribute("clientName", client);
        session.setAttribute("clientAddress", clientAddress);
        resp.sendRedirect("/ConfirmationInfo.do");
    }
}
