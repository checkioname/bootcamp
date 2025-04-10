package com.meli.calories.services;

import com.meli.calories.dtos.RecipieDTO;
import com.meli.calories.models.Food;

public interface RecipiesServiceInterface {
    int getTotalCalories();
    Food getIngredients(String name);
    String getMostCaloriesIngredient();
    RecipieDTO getRecipieInfo(String ingredientes);
}
