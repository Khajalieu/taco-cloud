package com.khaja.tacocloud.tacoorder.model;

public class IngredientRef {
    private String ingredient;

    public IngredientRef(String id) {
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    @Override
    public String toString() {
        return "IngredientRef [ingredient=" + ingredient + "]";
    }

}
