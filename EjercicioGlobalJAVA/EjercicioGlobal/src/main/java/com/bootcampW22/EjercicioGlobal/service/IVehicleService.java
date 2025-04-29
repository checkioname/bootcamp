package com.bootcampW22.EjercicioGlobal.service;

import com.bootcampW22.EjercicioGlobal.dto.VehicleDto;
import com.bootcampW22.EjercicioGlobal.exception.NotFoundException;

import java.util.List;
import java.util.Optional;

public interface IVehicleService {
    List<VehicleDto> searchAllVehicles();

    double getBrandMeanSpeed(String brand) throws NotFoundException;
    void bulkInsert(List<VehicleDto> vehiclesDtos);
    String updateSpeed(Long id, String speed);
    String addVehicle(VehicleDto vehicle);
    List<VehicleDto> findVehicles(Optional<String> color, Optional<Integer> year);
    List<VehicleDto> findVehicles(String brand, int start_date, int end_date);
    List<VehicleDto> getVehiclesByFuel(String type);
    void deleteById(Long id);
    List<VehicleDto> getVehicleByTransmission(String type);
    void updateFuelType(Long id, String type);
    Double getAverageBrandCapacity(String brand);
}