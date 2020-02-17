package mk.finki.ukim.mk.lab.repository.repositoryimpl;

import mk.finki.ukim.mk.lab.bootstrap.DataHolder;
import mk.finki.ukim.mk.lab.model.Order;
import mk.finki.ukim.mk.lab.repository.OrderRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class OrderRepositoryImp implements OrderRepository {
    @Override
    public void setNewOrder(Order order) {
        DataHolder.orders.add(order);
    }

    @Override
    public List<Order> getAllOrders() {
        return DataHolder.orders;
    }
}
