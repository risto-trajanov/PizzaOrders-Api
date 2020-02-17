package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.Ingredient;
import mk.finki.ukim.mk.lab.model.Pizza;
import mk.finki.ukim.mk.lab.model.exceptions.InvalidIngrediantException;
import mk.finki.ukim.mk.lab.model.exceptions.InvalidPageException;
import mk.finki.ukim.mk.lab.model.exceptions.SameIngrediantException;
import mk.finki.ukim.mk.lab.repository.IngredientRepository;
import mk.finki.ukim.mk.lab.repository.PizzaRepository;
import mk.finki.ukim.mk.lab.service.IngredientService;
import mk.finki.ukim.mk.lab.service.PizzaService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class IngredientImp implements IngredientService {
    private final IngredientRepository repository;
    private final PizzaRepository pizzaRepository;
    private static int spicyCount = 0;
    public IngredientImp(IngredientRepository repository, PizzaRepository pizzaRepository) {
        this.repository = repository;
        this.pizzaRepository = pizzaRepository;
    }

    @Override
    public Ingredient deleteById(int id) {
        Ingredient ingredient = this.repository.getByIngredientId(id).orElseThrow(() -> new InvalidIngrediantException("Losho id"));
        ingredient.setDeleted(true);
        return editIngredient(ingredient, id);
    }

    @Override
    public Ingredient saveIngredient(Ingredient ingredient) throws InvalidIngrediantException, SameIngrediantException {
        if(repository.getAllIngredient().stream().map(ingredient1 -> ingredient1.getName()).anyMatch(name -> ingredient.getName().equals(name))) {
            throw new SameIngrediantException();
        }
        if(spicyCount == 3){
            throw new InvalidIngrediantException("Premnogu spicy sostojki vo menito!");
        }
        if(ingredient.isSpicy()){
            spicyCount++;
        }
        return this.repository.saveIngredient(ingredient);
    }

    @Override
    public Ingredient editIngredient(Ingredient Ingredient, int id) {
        Ingredient oldIngredient = this.repository.getByIngredientId(id).orElseThrow(() -> new InvalidIngrediantException("Ne validno id"));
        oldIngredient.setAmount(Ingredient.getAmount());
        oldIngredient.setName(Ingredient.getName());
        oldIngredient.setSpicy(Ingredient.isSpicy());
        oldIngredient.setVeggie(Ingredient.isVeggie());
        oldIngredient.setDeleted(Ingredient.isDeleted());
        return this.repository.saveIngredient(oldIngredient);
    }

    @Override
    public Optional<Ingredient> getByIngredientId(int id) {
        return repository.getByIngredientId(id);
    }

    @Override
    public Page<Ingredient> pageIngredients(int page, int size) {
        if (page > 10){
            throw new InvalidPageException();
        }
        return repository.pageIngredients(page, size);
    }
    @Override
    public List<Pizza> getPizzasWithIng(int id) {
        Optional<Ingredient> ingredient = repository.getByIngredientId(id);
        return pizzaRepository.getAllByIngredientsContains(ingredient);
    }
    @Override
    public List<Ingredient> getAllSpicy(String spicy) {
        boolean isSpicy = spicy.equals("true") ? true : false;
        return repository.getAllIfSpicy(isSpicy);
    }

    @Override
    public List<Ingredient> searchIngredients(String name) {
        return this.repository.searchIngredient(name);
    }
}
