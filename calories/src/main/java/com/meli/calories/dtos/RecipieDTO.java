package com.meli.calories.dtos;

import com.meli.calories.models.Food;
import java.util.List;

public class RecipieDTO {
    private int totalCalories;
    private List<Food> ingredients;
    private Food mostCalorieIngredient;

    public RecipieDTO() {
    }

    public RecipieDTO(int totalCalories, List<Food> ingredients, Food mostCalorieIngredient) {
        this.totalCalories = totalCalories;
        this.ingredients = ingredients;
        this.mostCalorieIngredient = mostCalorieIngredient;
    }
}
