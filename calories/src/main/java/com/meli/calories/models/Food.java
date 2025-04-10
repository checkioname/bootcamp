package com.meli.calories.models;

public class Food {
    private String name;
    private int calories;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public Food() {
    }

    public Food(String name, int calories) {
        this.name = name;
        this.calories = calories;
    }
}
