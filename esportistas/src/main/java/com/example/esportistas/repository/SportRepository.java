package com.example.esportistas.repository;

import com.example.esportistas.models.Sport;

import java.util.List;

public class SportRepository {
    private static List<Sport> sports = List.of(
            new Sport("Tenis", 1),
            new Sport("Basquete", 2),
            new Sport("Futebol", 4)
    );

    public List<Sport> getSports() {
        return sports;
    }

    public String getByName(String name) {
        return sports.stream().filter(s -> s.getName().equals(name)).limit(1).toString();
    }
}
