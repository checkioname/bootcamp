package com.meli.concessionaria.controllers.responses;

import com.meli.concessionaria.models.Services;

import java.util.Date;

public class CarDetailsResponse {
    private String brand;
    private String model;
    private Date manufacuringDate;
    private int numberOfKilometers;
    private int doors;
    private double price;
    private String currency;
    private int countOfOwners;

    private Services service;

    public CarDetailsResponse() {
    }

    public CarDetailsResponse(String brand, String model, Date manufacuringDate, int numberOfKilometers, int doors, double price, String currency, int countOfOwners) {
        this.brand = brand;
        this.model = model;
        this.manufacuringDate = manufacuringDate;
        this.numberOfKilometers = numberOfKilometers;
        this.doors = doors;
        this.price = price;
        this.currency = currency;
        this.countOfOwners = countOfOwners;
    }

}
