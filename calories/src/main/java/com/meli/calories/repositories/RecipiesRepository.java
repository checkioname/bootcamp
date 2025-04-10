package com.meli.calories.repositories;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.calories.dtos.RecipieDTO;
import com.meli.calories.models.Food;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.Comparator;
import java.util.List;

@Repository
public class RecipiesRepository implements RecipiesRepositoryInterface {
    private List<Food> repo;
    ObjectMapper objectMapper = new ObjectMapper();

    public RecipiesRepository() {
        try {
            repo = objectMapper.readValue(new File("/Users/lggargalhone/Documents/bootcamp/github/calories/src/main/java/com/meli/calories/repositories/1. c. food.json"), new TypeReference<List<Food>>() {
            });
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }

    public int getTotalCalories() {
        return repo.stream().mapToInt(c -> c.getCalories()).sum();
    }

    public Food getIngredients(String name) {
        return repo.stream()
                .filter(c -> c.getName().equals(name))
                .findFirst()
                .get();
    }

    public String getMostCaloriesIngredient() {
        return repo.stream()
                .max(Comparator.comparing(Food::getCalories))
                .get().getName();
    }

    public RecipieDTO getRecipieInfo(String ingredientes) {
        var ingredientesNameList = ingredientes.split(",");
        var ingredientsList = repo.stream().filter(c -> c.getName().equals(ingredientesNameList)).toList();
        var totalCalories = ingredientsList.stream().mapToInt(c -> c.getCalories()).sum();
        var mostCalories = ingredientsList.stream().max(Comparator.comparing(Food::getCalories)).get();

        return new RecipieDTO(totalCalories, ingredientsList, mostCalories);
    }
}