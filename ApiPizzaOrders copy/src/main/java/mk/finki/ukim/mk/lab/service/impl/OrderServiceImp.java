package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.Order;
import mk.finki.ukim.mk.lab.repository.OrderRepository;
import mk.finki.ukim.mk.lab.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImp implements OrderService {

    private final OrderRepository orderRepository;
    private static Long id;
    public OrderServiceImp(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
        id = Long.valueOf(0);
    }

    @Override
    public void placeOrder(String pizzaType, String size, String clientName, String address) {
        orderRepository.setNewOrder(new Order(pizzaType,size, clientName, address, id++));
    }

    @Override
    public List<Order> getOrders() {
        return orderRepository.getAllOrders();
    }
}
