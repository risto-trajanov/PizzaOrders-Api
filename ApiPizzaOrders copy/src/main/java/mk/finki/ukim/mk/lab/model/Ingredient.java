package mk.finki.ukim.mk.lab.model;

import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Where(clause = "deleted=false")
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private boolean spicy;
    private float amount;
    private boolean veggie;
    private boolean deleted = false;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    List<Pizza> pizzas;

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public void setPizzas(List<Pizza> pizzas) {
        this.pizzas = pizzas;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public List<Pizza> getPizzas() {
        return pizzas;
    }

    public Ingredient(String name, boolean spicy, float amount, boolean veggie) {
        this.name = name;
        this.spicy = spicy;
        this.amount = amount;
        this.veggie = veggie;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSpicy(boolean spicy) {
        this.spicy = spicy;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public void setVeggie(boolean veggie) {
        this.veggie = veggie;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isSpicy() {
        return spicy;
    }

    public float getAmount() {
        return amount;
    }

    public boolean isVeggie() {
        return veggie;
    }
}
