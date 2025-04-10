package com.example.esportistas.controllers;

import com.example.esportistas.repository.SportRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class SportController {
    private static SportRepository repo;

    @GetMapping("/findSports/")
    public String findSports() {
        return "todos os esportes";
    }

    @GetMapping("/findSport/{name}")
    public String findSport(@PathVariable String name) {
        return repo.getByName(name);
    }



}
