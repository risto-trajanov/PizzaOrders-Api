package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.Ingredient;
import mk.finki.ukim.mk.lab.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface JpaPizzaRepository extends JpaRepository<Pizza, Integer>, PagingAndSortingRepository<Pizza, Integer> {
    List<Pizza> findAllByIngredients_SpicyIsTrue();
    Pizza deletePizzaById(int id);
    Optional<Pizza> getPizzaById(int id);
    List<Pizza> findAll();
    List<Pizza> findAllByIngredientsContains(Optional<Ingredient> ingredient);

}
