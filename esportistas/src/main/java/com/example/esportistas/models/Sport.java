package com.example.esportistas.models;

public class Sport {
    private String name;
    private int level;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Sport(String name, int level) {
        this.name = name;
        this.level = level;
    }
}
