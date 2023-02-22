package com.khaja.tacocloud.ingredient.repository;

import java.util.Optional;

import com.khaja.tacocloud.ingredient.model.Ingredient;

public interface JdbcIngredientRepository {

    Iterable<Ingredient> findAll();

    Optional<Ingredient> findById(String id);

    Ingredient save(Ingredient ingredient);
}
