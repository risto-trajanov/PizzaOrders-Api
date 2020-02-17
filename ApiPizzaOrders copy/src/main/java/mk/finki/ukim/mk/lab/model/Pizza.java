package mk.finki.ukim.mk.lab.model;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.GenerationType.AUTO;

@NoArgsConstructor

@Entity
public class Pizza {
    @Id
    @GeneratedValue(strategy = AUTO)
    private int id;
    private String name;
    private String description;
    private boolean veggie;
    @ManyToMany(cascade = CascadeType.MERGE)
    private List<Ingredient> ingredients;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isVeggie() {
        return veggie;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setVeggie(boolean veggie) {
        this.veggie = veggie;
    }

    public Pizza(String name, String description, boolean veggie, List<Ingredient> ingredientList){
        this.name = name;
        this.description = description;
        this.veggie = veggie;
        this.ingredients = ingredientList;
    }
    public Pizza(String pizza, String description) {
        name = pizza;
        this.description = description;
    }
}
