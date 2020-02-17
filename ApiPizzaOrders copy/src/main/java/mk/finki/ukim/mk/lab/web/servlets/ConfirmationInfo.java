package mk.finki.ukim.mk.lab.web.servlets;


import mk.finki.ukim.mk.lab.service.OrderService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "confirmation", urlPatterns = "/ConfirmationInfo.do")
public class ConfirmationInfo extends HttpServlet {

    private final OrderService orderService;
    private final SpringTemplateEngine springTemplateEngine;

    public ConfirmationInfo(OrderService orderService, SpringTemplateEngine springTemplateEngine) {
        this.orderService = orderService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext webContext = new WebContext(req,resp,req.getServletContext());
        HttpSession session = req.getSession();
        String name = (String) session.getAttribute("pizzaName");
        webContext.setVariable("pizzaName", name);
        String size = (String) session.getAttribute("pizzaSize");
        webContext.setVariable("pizzaSize", size);
        String clientName = (String) session.getAttribute("clientName");
        webContext.setVariable("clientName", clientName);
        String clientAddress = (String) session.getAttribute("clientAddress");
        webContext.setVariable("clientAddress", clientAddress);
        orderService.placeOrder(name,size,clientName,clientAddress);
        webContext.setVariable("clientIp", req.getRemoteAddr());
        String browserDetails = req.getHeader("User-Agent");
        webContext.setVariable("clientBrowser", browserDetails);
        resp.setContentType("text/html; charset=UTF-8");
        springTemplateEngine.process("confirmationInfo.html", webContext, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        resp.sendRedirect("/");
    }
}
