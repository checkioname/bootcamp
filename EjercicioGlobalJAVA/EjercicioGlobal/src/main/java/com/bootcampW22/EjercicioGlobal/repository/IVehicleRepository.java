package com.bootcampW22.EjercicioGlobal.repository;

import com.bootcampW22.EjercicioGlobal.entity.Vehicle;

import java.util.List;
import java.util.Optional;

public interface IVehicleRepository {
    List<Vehicle> findAll();
    Optional<Vehicle> findById(Long id);
    boolean addVehicle(Vehicle vehicle);
    List<Vehicle> findVehicles(String color, int year);
    List<Vehicle> findVehicles(String brand, int start_date, int end_date);
    void bulkInsert(List<Vehicle> vehicles);
    List<Long> getDuplicatedVehiclesIds(List<Vehicle> vehicles);
    void updateSpeed(Long id, String speed);
    List<Vehicle> getVehiclesByFuel(String type);
    List<Vehicle> getVehiclesByTransmission(String type);
    void updateFuelType(Long id, String type);

    boolean deleteVehicle(Long id);

    List<Vehicle> getVehiclesByBrand(String brand);
}
