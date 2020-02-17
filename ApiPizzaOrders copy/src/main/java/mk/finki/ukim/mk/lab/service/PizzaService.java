package mk.finki.ukim.mk.lab.service;
import mk.finki.ukim.mk.lab.model.Ingredient;
import mk.finki.ukim.mk.lab.model.Pizza;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public interface PizzaService {
    List<Pizza> listPizzas();
    Pizza deleteById(int id);
    Pizza savePizza(String name, String desc, boolean veggie, List<String> ingred);
    Pizza editPizza(String name, String desc, boolean veggie, List<String> ingred, int id);
    Optional<Pizza> getByPizzaId(int id);
    Page<Pizza> pagePizzas(int page, int size);
    List<Pizza> getPizzasWithLessIng(int numberOfIngred);
    List<Ingredient> getPizzasWithSameIngredients(int id1, int id2);
    List<Pizza> getAllSpicyPizzas();
}
