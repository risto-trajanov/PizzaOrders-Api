package mk.finki.ukim.mk.lab.web.rest;

import mk.finki.ukim.mk.lab.model.Ingredient;
import mk.finki.ukim.mk.lab.model.Pizza;
import mk.finki.ukim.mk.lab.service.PizzaService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "/api/pizzas", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
public class PizzaApi {
    private final PizzaService service;

    public PizzaApi(PizzaService service) {
        this.service = service;
    }

    @GetMapping
    public Page<Pizza> getAllPizza(@RequestHeader(name = "page", defaultValue = "0", required = false) int page,
                                   @RequestHeader(name = "page-size", defaultValue = "5", required = false) int size){

        return service.pagePizzas(page, size);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createPizza(@RequestHeader("name") String name,
                                  @RequestHeader("desc") String description,
                                  @RequestHeader(value = "veggie", required = false, defaultValue = "false") boolean veggie,
                                  @RequestHeader("ingred") List<String> ingredientList) {

        service.savePizza(name, description, veggie, ingredientList);
    }
    @GetMapping("/{id}")
    public Optional<Pizza> getById(@PathVariable int id){
        return service.getByPizzaId(id);
    }
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void editPizza(@PathVariable int id,
                                     @RequestHeader("name") String name,
                                     @RequestHeader("desc") String description,
                                     @RequestHeader("veggie") boolean veggie,
                                     @RequestHeader("ingred") List<String> ingredientList) {

        service.editPizza(name, description, veggie, ingredientList, id);
    }
    @DeleteMapping("/{id}")
    public Pizza deletePizza(@PathVariable int id){
        return service.deleteById(id);
    }

    @GetMapping("/as")
    public List<Pizza> getAllPizzasWithLessIng(@RequestParam("totalIngredients") int numIng){
        return service.getPizzasWithLessIng(numIng);
    }
    @GetMapping("/compare")
    public List<Ingredient> getSameIngredients(@RequestParam("pizza1") int id1,
                                               @RequestParam("pizza2") int id2){
        return service.getPizzasWithSameIngredients(id1, id2);
    }


}
