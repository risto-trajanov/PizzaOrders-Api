package mk.finki.ukim.mk.lab.repository;
import mk.finki.ukim.mk.lab.model.Ingredient;
import mk.finki.ukim.mk.lab.model.Pizza;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
@Repository
public interface PizzaRepository {

    List<Pizza> getAllPizzas();
    Pizza deleteById(int id);
    Pizza savePizza(Pizza pizza);
    Optional<Pizza> getByPizzaId(int id);
    Page<Pizza> pagePizzas(int page, int size);
    List<Pizza> allPizzasWithSpicy_True();
    List<Pizza> getAllByIngredientsContains(Optional<Ingredient> ingredient);


}
