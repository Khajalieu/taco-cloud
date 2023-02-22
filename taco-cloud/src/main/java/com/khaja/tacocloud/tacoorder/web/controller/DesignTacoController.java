package com.khaja.tacocloud.tacoorder.web.controller;

import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.khaja.tacocloud.ingredient.model.Ingredient;
import com.khaja.tacocloud.ingredient.model.Ingredient.Type;
import com.khaja.tacocloud.ingredient.repository.JdbcIngredientRepository;
import com.khaja.tacocloud.tacoorder.model.Taco;
import com.khaja.tacocloud.tacoorder.model.TacoOrder;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/api/design")
@SessionAttributes("tacoOrder")
public class DesignTacoController {
    private static final Logger log = LoggerFactory.getLogger(DesignTacoController.class);
    private JdbcIngredientRepository ingredientRepository;

    public DesignTacoController(JdbcIngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @GetMapping
    public String showDesignForm() {
        return "design";
    }

    @PostMapping
    public String processTaco(@Valid Taco taco,
            Errors errors, @ModelAttribute TacoOrder tacoOrder) {

        if (errors.hasErrors()) {
            return "design";
        }
        log.info("Processing taco: {}", taco);
        log.info("{}", tacoOrder);
        tacoOrder.addTaco(taco);

        return "redirect:/api/orders/current";
    }

    @ModelAttribute(name = "tacoOrder")
    public TacoOrder order() {
        return new TacoOrder();
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        // List<Ingredient> ingredients = Arrays.asList(
        // new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
        // new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
        // new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
        // new Ingredient("CARN", "Carnitas", Type.PROTEIN),
        // new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
        // new Ingredient("LETC", "Lettuce", Type.VEGGIES),
        // new Ingredient("CHED", "Cheddar", Type.CHEESE),
        // new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
        // new Ingredient("SLSA", "Salsa", Type.SAUCE),
        // new Ingredient("SRCR", "Sour Cream", Type.SAUCE));
        Iterable<Ingredient> ingredients = ingredientRepository.findAll();
        Type[] types = Ingredient.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
        }
    }

    private Iterable<Ingredient> filterByType(Iterable<Ingredient> ingredients, Type type) {
        Spliterator<Ingredient> spliterator = Spliterators.spliteratorUnknownSize(ingredients.iterator(), 0);

        return StreamSupport.stream(spliterator, false)
                .filter(ingredient -> ingredient.getType().equals(type))
                .collect(Collectors.toList());
    }
}
