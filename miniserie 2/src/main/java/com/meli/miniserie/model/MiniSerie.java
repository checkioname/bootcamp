package com.meli.miniserie.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class MiniSerie {
    @Id
    @GeneratedValue()
    private Long id;
    private String name;
    private Double rating;
    private int amount_of_awards;

}
