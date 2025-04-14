package com.meli.concessionaria.controllers.request;

import com.meli.concessionaria.models.Car;

import java.util.Date;

public record VehicleRequest (String brand, String model, Date manufacturingDate, int km, int doors, double price, String currency, int countOfOwners) {
    public Car toCarModel() {
        return new Car(brand, model, manufacturingDate, km, doors, price, currency, countOfOwners);
    }
}
