package com.meli.calories.controllers;

import com.meli.calories.dtos.RecipieDTO;
import com.meli.calories.models.Food;
import com.meli.calories.services.RecipiesServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/recipies")
public class RecipiesController {

    @Autowired
    private static RecipiesServiceInterface service;

    @Autowired
    public RecipiesController(RecipiesServiceInterface s) {
        service = s;
    }

    @GetMapping("/total-calories")
    public int getTotalCalories() {
        return service.getTotalCalories();
    }

    @GetMapping("/ingredients")
    public Food getIngredients(@RequestParam String name) {
        return service.getIngredients(name);
    }

    @GetMapping("/most-calories")
    public String getMostCaloriesIngredient() {
        return service.getMostCaloriesIngredient();
    }

    @GetMapping("/recipient")
    public RecipieDTO getRecipieInfo(@RequestParam String ingredientes) {
        return service.getRecipieInfo(ingredientes);
    }
}
