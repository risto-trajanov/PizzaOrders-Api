package mk.finki.ukim.mk.lab.bootstrap;

import mk.finki.ukim.mk.lab.model.Ingredient;
import mk.finki.ukim.mk.lab.model.Order;
import mk.finki.ukim.mk.lab.model.Pizza;
import mk.finki.ukim.mk.lab.repository.JpaIngredientsRepository;
import mk.finki.ukim.mk.lab.repository.JpaPizzaRepository;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Component
public class DataHolder {
    public static int num = 0;
    public static final List<Pizza> pizzas = new LinkedList<>();
    public  static final List<Order> orders = new ArrayList<>();
    public  static final List<Ingredient> ingredients = new ArrayList<>();
    public  final JpaIngredientsRepository jpaIngredientsRepository;
    public  final JpaPizzaRepository jpaPizzaRepository;

    public DataHolder(JpaPizzaRepository jpaPizzaRepository, JpaIngredientsRepository jpaIngredientsRepository) {
        this.jpaIngredientsRepository = jpaIngredientsRepository;
        this.jpaPizzaRepository = jpaPizzaRepository;
    }

    @PostConstruct
    public void init(){
        ingredients.add(new Ingredient("tomato sauce", false, 5, true));
        ingredients.add(new Ingredient("mozzarella", false, 5, false));
        ingredients.add(new Ingredient("fresh cream", false, 2, true));
        ingredients.add(new Ingredient("bacon", false, 5, false));
        ingredients.add(new Ingredient("mushroomse", false, 2, true));
        ingredients.add(new Ingredient("cheddar", false, 5, false));
        ingredients.add(new Ingredient("barbecue sauce", true, 5, false));
        ingredients.add(new Ingredient("ham", false, 5, false));
        ingredients.add(new Ingredient("sausage", true, 5, false));
        ingredients.add(new Ingredient("pepperoni", true, 5, false));
        ingredients.add(new Ingredient("onions", true, 5, true));
        ingredients.add(new Ingredient("pizza sauce", false, 5, false));
        ingredients.add(new Ingredient("olive oil", false, 5, true));
        ingredients.add(new Ingredient("cheese", false, 5, false));
        ingredients.add(new Ingredient("chicken meat", false, 5, false));
        ingredients.add(new Ingredient("Parmesan", false, 5, false));
        List<Ingredient> MargIng = new LinkedList<>();
        MargIng.add(ingredients.get(0));
        MargIng.add(ingredients.get(1));
        pizzas.add(new Pizza("Margherita", "tomato sauce, mozzarella", true, MargIng));
        List<Ingredient> CarbIng = new LinkedList<>();
        CarbIng.add(ingredients.get(2));
        CarbIng.add(ingredients.get(1));
        CarbIng.add(ingredients.get(3));
        pizzas.add(new Pizza("Carbonara", "fresh cream, mozzarella, bacon", false, CarbIng));
        pizzas.add(new Pizza("Vegetariana", "tomato sauce, mushroomstomato sauce, mushrooms"));
        pizzas.add(new Pizza("Calzone", "Pizza dough, ricotta, pepperoni, pizza sauce, olive oil"));
        pizzas.add(new Pizza("Cheddar", "cheddar, tomato sauce"));
        pizzas.add(new Pizza("Capricciosa", "tomato sauce, cheese, ham"));
        pizzas.add(new Pizza("Burger Classic", "barbecue sauce, beef, mozzarella, onions"));
        pizzas.add(new Pizza("Boston Barbecue", "ham, chicken meat, onions"));
        pizzas.add(new Pizza("Vegie Vulcano", "tomato sauce, mozzarella, sausage"));
        pizzas.add(new Pizza("Boston Barbecue", "Taleggio, Mascarpone, Gorgonzola, Parmesan"));

        if (num == 0) {
            this.jpaIngredientsRepository.saveAll(ingredients);
            this.jpaPizzaRepository.saveAll(pizzas);
            num++;
        }


    }
}
