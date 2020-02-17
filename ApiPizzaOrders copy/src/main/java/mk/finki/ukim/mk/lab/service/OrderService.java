package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    void placeOrder(String pizzaType, String size, String clientName, String address);
    List<Order> getOrders();
}
