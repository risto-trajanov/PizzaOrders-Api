package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.Ingredient;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface JpaIngredientsRepository extends JpaRepository<Ingredient, Integer>, PagingAndSortingRepository<Ingredient, Integer> {

    Ingredient deleteById(int id);
    List<Ingredient> findAll();
    Optional<Ingredient> getById(int id);
    List<Ingredient> getIngredientsBySpicy(boolean spicy);
    @Query("select i from Ingredient i " +
            "WHERE i.name like CONCAT('%', :name, '%')")
    List<Ingredient> searchIngredient(@Param("name") String name);



}
