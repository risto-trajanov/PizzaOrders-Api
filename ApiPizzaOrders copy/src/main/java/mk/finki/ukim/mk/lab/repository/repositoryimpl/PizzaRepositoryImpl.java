package mk.finki.ukim.mk.lab.repository.repositoryimpl;
import mk.finki.ukim.mk.lab.model.Ingredient;
import mk.finki.ukim.mk.lab.model.Pizza;
import mk.finki.ukim.mk.lab.repository.JpaPizzaRepository;
import mk.finki.ukim.mk.lab.repository.PizzaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
@Repository
public class PizzaRepositoryImpl implements PizzaRepository {
    private final JpaPizzaRepository repository;
    public PizzaRepositoryImpl(JpaPizzaRepository repository) {
        this.repository = repository;
     }
    @Override
    public List<Pizza> getAllPizzas() {
        return repository.findAll();
    }
    @Override
    public Pizza deleteById(int id) {
        return this.repository.deletePizzaById(id);
    }
    @Override
    public Pizza savePizza(Pizza pizza) {
        return this.repository.save(pizza);
    }
    @Override
    public Optional<Pizza> getByPizzaId(int id) {
        return this.repository.getPizzaById(id);
    }
    @Override
    public Page<Pizza> pagePizzas(int page, int size) {
        return this.repository.findAll(PageRequest.of(page, size));
    }
    @Override
    public List<Pizza> allPizzasWithSpicy_True() {
        return this.repository.findAllByIngredients_SpicyIsTrue();
    }

    @Override
    public List<Pizza> getAllByIngredientsContains(Optional<Ingredient> ingredient) {
        return repository.findAllByIngredientsContains(ingredient);
    }
}
