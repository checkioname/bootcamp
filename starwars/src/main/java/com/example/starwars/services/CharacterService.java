package com.example.starwars.services;

import com.example.starwars.dtos.CharacterDTO;
import com.example.starwars.repositories.CharacterRepository;
import org.springframework.stereotype.Service;

@Service
public class CharacterService {
    private static CharacterRepository repo;

    public CharacterDTO getCharacter(String name) {
        if (isValidEntry(name)) {
            return repo.getCharacterByName(name);
        }
        return null;
    }

    private boolean isValidEntry(String name) {return true;}

}

