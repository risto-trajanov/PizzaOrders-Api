package mk.finki.ukim.mk.lab.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Order {

    String pizzaType, pizzaSize, clientName, clientAddress;
    Long orderId;

    public Order(String pizzaType, String pizzaSize, String clientName, String clientAddress, Long orderId) {
        this.pizzaType = pizzaType;
        this.pizzaSize = pizzaSize;
        this.clientName = clientName;
        this.clientAddress = clientAddress;
        this.orderId = orderId;
    }
}
