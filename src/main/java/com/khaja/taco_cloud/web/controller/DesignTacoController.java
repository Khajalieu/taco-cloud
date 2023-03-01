package com.khaja.taco_cloud.web.controller;

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

import com.khaja.taco_cloud.domain.Ingredient;
import com.khaja.taco_cloud.domain.Ingredient.Type;
import com.khaja.taco_cloud.domain.Taco;
import com.khaja.taco_cloud.domain.TacoOrder;
import com.khaja.taco_cloud.repository.IngredientRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/api/design")
@SessionAttributes("tacoOrder")
public class DesignTacoController {
    private static final Logger log = LoggerFactory.getLogger(DesignTacoController.class);
    private IngredientRepository ingredientRepository;

    public DesignTacoController(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @GetMapping
    public String showDesignForm() {
        return "design";
    }

    @PostMapping
    public String processTaco(@Valid Taco taco,
            Errors errors, @ModelAttribute TacoOrder tacoOrder) {
                log.info("taco ingredients: {}", taco.getIngredients());
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
