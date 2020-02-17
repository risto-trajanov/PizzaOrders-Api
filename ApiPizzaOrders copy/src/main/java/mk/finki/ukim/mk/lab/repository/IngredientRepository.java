package mk.finki.ukim.mk.lab.repository;
import mk.finki.ukim.mk.lab.model.Ingredient;
import org.springframework.data.domain.Page;
import java.util.List;
import java.util.Optional;

public interface IngredientRepository {
    Ingredient deleteById(int id);
    Ingredient saveIngredient(Ingredient Ingredient);
    Optional<Ingredient> getByIngredientId(int id);
    Page<Ingredient> pageIngredients(int page, int size);
    List<Ingredient> getAllIngredient();
    List<Ingredient> getAllIfSpicy(boolean spicy);
    List<Ingredient> searchIngredient(String name);
}
