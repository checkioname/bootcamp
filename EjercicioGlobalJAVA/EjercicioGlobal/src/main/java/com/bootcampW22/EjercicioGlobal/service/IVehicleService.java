package com.bootcampW22.EjercicioGlobal.service;

import com.bootcampW22.EjercicioGlobal.dto.VehicleDto;

import java.util.List;

public interface IVehicleService {
    List<VehicleDto> searchAllVehicles();

    double getBrandMeanSpeed(String brand);

    void deleteVehicle(Long id);
    boolean addVehicle(VehicleDto vehicle);
    List<VehicleDto> findVehicles(String color, int year);
    List<VehicleDto> findVehicles(String brand, int start_date, int end_date);
}
