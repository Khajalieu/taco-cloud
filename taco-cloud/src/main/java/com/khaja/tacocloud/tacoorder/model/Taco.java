package com.khaja.tacocloud.tacoorder.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.khaja.tacocloud.ingredient.model.Ingredient;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Taco {
   
    private Long id;
  
    private Date createdAt = new Date();
  
    @NotNull
    @Size(min=5, message="Name must be at least 5 characters long")
    private String name;
  
    @Size(min=1, message="You must choose at least 1 ingredient")
    private List<IngredientRef> ingredients = new ArrayList<>();
  
    public void addIngredient(Ingredient taco) {
      this.ingredients.add(new IngredientRef(taco.getId()));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<IngredientRef> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<IngredientRef> ingredients) {
        this.ingredients = ingredients;
    }

    

  

}
