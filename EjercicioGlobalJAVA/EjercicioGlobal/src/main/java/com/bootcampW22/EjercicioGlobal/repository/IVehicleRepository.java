package com.bootcampW22.EjercicioGlobal.repository;

import com.bootcampW22.EjercicioGlobal.entity.Vehicle;

import java.util.List;
import java.util.Optional;

public interface IVehicleRepository {
    List<Vehicle> findAll();
    boolean deleteVehicle(Long id);
    Optional<Vehicle> findById(Long id);
    boolean addVehicle(Vehicle vehicle);
    List<Vehicle> findVehicles(String color, int year);
    List<Vehicle> findVehicles(String brand, int start_date, int end_date);

    double meanSpeedByBrand(String brand);
}
