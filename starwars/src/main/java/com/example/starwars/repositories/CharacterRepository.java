package com.example.starwars.repositories;

import com.example.starwars.dtos.CharacterDTO;
import com.example.starwars.models.Character;

import java.util.List;

public class CharacterRepository {
    private static List<Character> repo = List.of(
            new Character(),
            new Character(),
            new Character(),
            new Character(),
            new Character(),
            new Character(),
            new Character()
    );


    public CharacterDTO getCharacterByName(String name) {
        var character =  repo.stream()
                                        .filter(c -> c.getName().equals(name))
                                        .findFirst().get();
        return new CharacterDTO(character);
    }
}
