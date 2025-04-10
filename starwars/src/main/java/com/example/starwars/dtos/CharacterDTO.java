package com.example.starwars.dtos;

import com.example.starwars.models.Character;

public record CharacterDTO(String name, int height, int mass, String gender, String homeWorld, String species ) {
    public CharacterDTO(Character c) {
        this(c.getName(), c.getHeight(), c.getMass(), c.getGender(), c.getHomeWorld(), c.getSpecies());
    }
}
