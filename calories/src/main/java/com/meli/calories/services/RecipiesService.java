package com.meli.calories.services;

import com.meli.calories.dtos.RecipieDTO;
import com.meli.calories.models.Food;
import com.meli.calories.repositories.RecipiesRepository;
import com.meli.calories.repositories.RecipiesRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecipiesService implements RecipiesServiceInterface{

    @Autowired
    private static RecipiesRepositoryInterface repo;

    @Autowired
    public RecipiesService(RecipiesRepositoryInterface r) {
        repo = r;
    }

    public int getTotalCalories() {
        return repo.getTotalCalories();
    }

    public Food getIngredients(String name) {
            if (isEntryValid())
                return repo.getIngredients(name);
            return null;
    }

    public String getMostCaloriesIngredient() {
        return repo.getMostCaloriesIngredient();
    }

    public RecipieDTO getRecipieInfo(String ingredientes) {
            if (isEntryValid())
                return repo.getRecipieInfo(ingredientes);
            return null;
    }

    private boolean isEntryValid() { return true;}
}

