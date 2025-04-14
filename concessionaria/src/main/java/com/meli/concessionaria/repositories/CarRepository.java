package com.meli.concessionaria.repositories;

import com.meli.concessionaria.models.Car;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class CarRepository implements ICarRepository{
   private static List<Car> db;

   public CarRepository() {
       this.db =new ArrayList<>(List.of(new Car("nissan"), new Car("ford"), new Car("ferrari")));
   }

   public boolean addVehicle(Car car) {
       return db.add(car);
   }

   public List<Car> getVehicles() {
       return db;
   }

   public List<Car> getVehicles(Date date) {
       return db.stream().filter(v -> v.getManufacuringDate().after(date) || v.getManufacuringDate().equals(date)).toList();
   }

    public List<Car> getVehicles(String color, int year) {
        return db.stream().filter(v -> v.getColor().equals(color) && v.getYear() == year).toList();
    }

   public List<Car> getVehicle(double price) {
       return db.stream().filter(v -> v.getPrice() >= price).toList();
   }

   public Car getVehicleDetails(int id) {
       return db.stream().filter(v -> v.getId() == id).findFirst().get();
   }
}
