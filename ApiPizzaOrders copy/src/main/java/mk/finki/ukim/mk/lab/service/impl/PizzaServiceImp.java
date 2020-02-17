package mk.finki.ukim.mk.lab.service.impl;
import mk.finki.ukim.mk.lab.model.Ingredient;
import mk.finki.ukim.mk.lab.model.Pizza;
import mk.finki.ukim.mk.lab.model.exceptions.InvalidPizzaException;
import mk.finki.ukim.mk.lab.repository.IngredientRepository;
import mk.finki.ukim.mk.lab.repository.PizzaRepository;
import mk.finki.ukim.mk.lab.service.PizzaService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class PizzaServiceImp implements PizzaService {
    private final PizzaRepository repository;
    private final IngredientRepository ingredientRepository;
    public PizzaServiceImp(PizzaRepository repository, IngredientRepository ingredientRepository) {
        this.repository = repository;
        this.ingredientRepository = ingredientRepository;
    }
    @Override
    public List<Pizza> listPizzas() {
        return repository.getAllPizzas();
    }
    @Override
    public Pizza deleteById(int id) {
        return repository.deleteById(id);
    }
    @Override
    public Pizza savePizza(String name, String desc, boolean veggie, List<String> ingred) {
        List<Ingredient> ingredients = ingredientRepository.getAllIngredient().stream()
                                        .filter(ingredient -> ingred.contains(ingredient.getName()))
                                        .collect(Collectors.toList());
        if(ingredients.stream().anyMatch(ingredient -> ingredient.isVeggie() != true)){
            veggie = false;
        }
        Pizza pizza = new Pizza(name, desc, veggie, ingredients);
        return this.repository.savePizza(pizza);
    }
    @Override
    public Pizza editPizza(String name, String desc, boolean veggie, List<String> ingred, int id) {
        List<Ingredient> ingredients = ingredientRepository.getAllIngredient().stream().filter(ingredient -> ingred.contains(ingredient)).collect(Collectors.toList());
        Pizza oldPizza = this.repository.getByPizzaId(id).orElseThrow(() -> new InvalidPizzaException("Ne validno Id za Pizza"));
        oldPizza.setDescription(desc);
        oldPizza.setIngredients(ingredients);
        oldPizza.setName(name);
        oldPizza.setVeggie(veggie);
        return this.repository.savePizza(oldPizza);
    }
    @Override
    public Optional<Pizza> getByPizzaId(int id) {
        return repository.getByPizzaId(id);
    }
    @Override
    public Page<Pizza> pagePizzas(int page, int size) {
        return repository.pagePizzas(page,size);
    }
    @Override
    public List<Pizza> getPizzasWithLessIng(int numberOfIngred) {
        return repository.getAllPizzas().stream()
                .filter(pizza -> pizza.getIngredients().stream().count() < numberOfIngred)
                .collect(Collectors.toList());
    }
    @Override
    public List<Ingredient> getPizzasWithSameIngredients(int id1, int id2) {
        Optional<Pizza> pizza1 = repository.getByPizzaId(id1);
        Optional<Pizza> pizza2 = repository.getByPizzaId(id2);
        return pizza1.get().getIngredients().stream().filter(ingredient -> pizza2.get().getIngredients().stream().anyMatch(ingredient1 -> ingredient1.equals(ingredient))).collect(Collectors.toList());
    }
    @Override
    public List<Pizza> getAllSpicyPizzas() {
        return repository.allPizzasWithSpicy_True();
    }
}
