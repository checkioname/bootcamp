package com.meli.concessionaria.repositories;

import com.meli.concessionaria.models.Car;

import java.util.Date;
import java.util.List;

public class CarRepository {
   private static List<Car> db = List.of(new Car(), new Car(), new Car());

   public boolean addVehicle(Car car) {
       return db.add(car);
   }

   public List<Car> getVehicle() {
       return db;
   }

   public List<Car> getVehicle(Date date) {
       return db.stream().filter(v -> v.getManufacuringDate().after(date) || v.getManufacuringDate().equals(date)).toList();
   }
   public List<Car> getVehicle(double price) {
       return db.stream().filter(v -> v.getPrice() >= price).toList();
   }

   public Car getVehicleDetails(int id) {
       return db.stream().filter(v -> v.getId() == id).findFirst().get();
   }
}
