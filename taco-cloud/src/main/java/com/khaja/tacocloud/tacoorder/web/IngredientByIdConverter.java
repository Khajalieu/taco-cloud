package com.khaja.tacocloud.tacoorder.web;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.khaja.tacocloud.ingredient.model.Ingredient;
import com.khaja.tacocloud.ingredient.repository.JdbcIngredientRepository;

@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {

    private JdbcIngredientRepository ingredientRepository;

    public IngredientByIdConverter(JdbcIngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public Ingredient convert(String id) {
        return ingredientRepository.findById(id).orElse(null);

    }

}
