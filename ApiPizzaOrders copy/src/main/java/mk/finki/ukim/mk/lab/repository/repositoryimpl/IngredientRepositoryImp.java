package mk.finki.ukim.mk.lab.repository.repositoryimpl;
import mk.finki.ukim.mk.lab.model.Ingredient;
import mk.finki.ukim.mk.lab.model.exceptions.InvalidIngrediantException;
import mk.finki.ukim.mk.lab.repository.IngredientRepository;
import mk.finki.ukim.mk.lab.repository.JpaIngredientsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
@Repository
public class IngredientRepositoryImp implements IngredientRepository {
    private final JpaIngredientsRepository repository;
    public IngredientRepositoryImp(JpaIngredientsRepository repository) {
        this.repository = repository;
    }
    @Override
    public Ingredient deleteById(int id) {
        Ingredient ingredient = getByIngredientId(id).orElseThrow(() -> new InvalidIngrediantException("Losho id"));
        this.repository.deleteById(id);
        return ingredient;
    }
    @Override
    public Ingredient saveIngredient(Ingredient Ingredient) {
        return this.repository.save(Ingredient);
    }
    @Override
    public Optional<Ingredient> getByIngredientId(int id) {
        return this.repository.getById(id);
    }
    @Override
    public Page<Ingredient> pageIngredients(int page, int size) {
        return repository.findAll(PageRequest.of(page,size, Sort.Direction.DESC, "name"));
    }
    @Override
    public List<Ingredient> getAllIngredient() {
        return repository.findAll();
    }
    @Override
    public List<Ingredient> getAllIfSpicy(boolean spicy) {
        return repository.getIngredientsBySpicy(spicy);
    }

    @Override
    public List<Ingredient> searchIngredient(String name) {
        return this.repository.searchIngredient(name.toLowerCase());
    }
}