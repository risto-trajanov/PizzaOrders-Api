package mk.finki.ukim.mk.lab.web.rest;

import mk.finki.ukim.mk.lab.model.Ingredient;
import mk.finki.ukim.mk.lab.model.Pizza;
import mk.finki.ukim.mk.lab.model.exceptions.InvalidIngrediantException;
import mk.finki.ukim.mk.lab.model.exceptions.SameIngrediantException;
import mk.finki.ukim.mk.lab.service.IngredientService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "/api/ingredients", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
public class IngredientApi {
    private final IngredientService service;
    public IngredientApi(IngredientService service) {
        this.service = service;
    }
    @GetMapping
    public Page<Ingredient> getAllIngredients(@RequestHeader(name = "page", defaultValue = "0", required = false) int page,
                                              @RequestHeader(name = "page-size", defaultValue = "5", required = false) int size){

        return service.pageIngredients(page, size);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Ingredient createIngredient(@RequestParam("name") String name,
                                       @RequestParam("spicy") boolean spicy,
                                       @RequestParam("amount") float amount,
                                       @RequestParam("veggie") boolean veggie,
                                       HttpServletResponse response,
                                       UriComponentsBuilder builder) throws InvalidIngrediantException, SameIngrediantException {

        return service.saveIngredient(new Ingredient(name, spicy, amount, veggie));
    }
    @GetMapping("/{id}")
    public Optional<Ingredient> getById(@PathVariable int id){
        return service.getByIngredientId(id);
    }
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Ingredient editIngredient(@PathVariable int id,
                                        @RequestParam("name") String name,
                                       @RequestParam("spicy") boolean spicy,
                                       @RequestParam("amount") float amount,
                                       @RequestParam("veggie") boolean veggie,
                                       HttpServletResponse response,
                                       UriComponentsBuilder builder) {

        return service.editIngredient(new Ingredient(name, spicy, amount, veggie), id);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Ingredient deleteIngredient(@PathVariable int id){
        return service.deleteById(id);
    }

    @GetMapping("/as")
    public List<Ingredient> allSpicy(@RequestParam("spicy") String spicy){
        return service.getAllSpicy(spicy);
    }

    @GetMapping("/{id}/pizzas")
    public List<Pizza> getPizzaWithIng(@PathVariable int id){
        return service.getPizzasWithIng(id);
    }

    @GetMapping(params = "name")
    public List<Ingredient> searchConsultationSlots(@RequestParam String name) {
        return service.searchIngredients(name);
    }


}
