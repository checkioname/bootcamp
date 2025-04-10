package com.meli.calories.controllers;

import com.meli.calories.dtos.RecipieDTO;
import com.meli.calories.models.Food;
import com.meli.calories.services.RecipiesServiceInterface;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController("recipies")
public class RecipiesController {
    private static RecipiesServiceInterface service;

    @GetMapping("/totalCalories/")
    public int getTotalCalories() {
        return service.getTotalCalories();
    }

    @GetMapping("ingredients/{name}")
    public Food getIngredients(String name) {
        return service.getIngredients(name);
    }

    @GetMapping("mostcalories")
    public String getMostCaloriesIngredient() {
        return service.getMostCaloriesIngredient();
    }

    @GetMapping("recipieinfo")
    public RecipieDTO getRecipieInfo(@PathVariable String ingredientes) {
        return service.getRecipieInfo(ingredientes);
    }
}
