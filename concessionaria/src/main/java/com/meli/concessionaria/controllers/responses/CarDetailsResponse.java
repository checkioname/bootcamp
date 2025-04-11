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
}
