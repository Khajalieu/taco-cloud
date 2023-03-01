package com.khaja.taco_cloud.repository;

import org.springframework.data.repository.CrudRepository;

import com.khaja.taco_cloud.domain.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, String>{
    
}
