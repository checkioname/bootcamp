package com.meli.concessionaria.repositories;

import com.meli.concessionaria.models.Car;

import java.util.List;

public interface ICarRepository {
   public boolean addVehicle(Car car);
   public List<Car> getVehicles(String color, int year);
}
