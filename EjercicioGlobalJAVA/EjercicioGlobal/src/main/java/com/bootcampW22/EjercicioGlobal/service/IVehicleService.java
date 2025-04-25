package com.bootcampW22.EjercicioGlobal.service;

import com.bootcampW22.EjercicioGlobal.dto.VehicleDto;
import com.bootcampW22.EjercicioGlobal.exception.NotFoundException;

import java.io.NotActiveException;
import java.util.List;
import java.util.Optional;

public interface IVehicleService {
    List<VehicleDto> searchAllVehicles();

    double getBrandMeanSpeed(String brand) throws NotFoundException;

    List<VehicleDto> bulkInsert(List<VehicleDto> vehiclesDtos);

    void updateSpeed(Long id, String speed);

    void deleteVehicle(Long id);
    String addVehicle(VehicleDto vehicle);
    List<VehicleDto> findVehicles(Optional<String> color, Optional<Integer> year);
    List<VehicleDto> findVehicles(String brand, int start_date, int end_date);
}
