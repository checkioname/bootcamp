package com.example.starwars.controllers;

import com.example.starwars.dtos.CharacterDTO;
import com.example.starwars.services.CharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/character/")
public class CharacterController {
    private static CharacterService service;

    @GetMapping("{name}")
    public CharacterDTO getCharacterByName(String name) {
        return service.getCharacter(name);
    }

}
