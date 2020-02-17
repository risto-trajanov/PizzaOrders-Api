package mk.finki.ukim.mk.lab.web.controllers;

import mk.finki.ukim.mk.lab.model.Order;
import mk.finki.ukim.mk.lab.service.OrderService;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class SavedOrders {
    private final OrderService orderService;

    public SavedOrders(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/savedOrders.do")
    public ModelAndView showOrdreds(HttpServletRequest request) {
        List<Order> orders = orderService.getOrders();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("savedOrders", orders);
        return modelAndView;
    }

    @PostMapping("/savedOrders.do")
    public String getBack(){
        return "redirect:/";
    }


}
