package com.meli.calories.repositories;

import com.meli.calories.dtos.RecipieDTO;
import com.meli.calories.models.Food;

public interface RecipiesRepositoryInterface {
    public int getTotalCalories();

    public Food getIngredients(String name);

    public String getMostCaloriesIngredient();

    RecipieDTO getRecipieInfo(String ingredientes);
}

