package mk.finki.ukim.mk.lab.service;
import mk.finki.ukim.mk.lab.model.Ingredient;
import mk.finki.ukim.mk.lab.model.Pizza;
import mk.finki.ukim.mk.lab.model.exceptions.InvalidIngrediantException;
import mk.finki.ukim.mk.lab.model.exceptions.SameIngrediantException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public interface IngredientService {
    Ingredient deleteById(int id);
    Ingredient saveIngredient(Ingredient Ingredient) throws InvalidIngrediantException, SameIngrediantException;
    Ingredient editIngredient(Ingredient Ingredient, int id);
    Optional<Ingredient> getByIngredientId(int id);
    Page<Ingredient> pageIngredients(int page, int size);
    List<Pizza> getPizzasWithIng(int id);
    List<Ingredient> getAllSpicy(String spicy);
    List<Ingredient> searchIngredients(String name);
}
