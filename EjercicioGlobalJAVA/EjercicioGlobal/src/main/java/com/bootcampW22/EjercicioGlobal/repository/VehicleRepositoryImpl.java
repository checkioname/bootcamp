package com.bootcampW22.EjercicioGlobal.repository;

import com.bootcampW22.EjercicioGlobal.entity.Vehicle;
import com.bootcampW22.EjercicioGlobal.exception.InvalidVehicleException;
import com.bootcampW22.EjercicioGlobal.exception.NotFoundException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class VehicleRepositoryImpl implements IVehicleRepository{

    private List<Vehicle> listOfVehicles = new ArrayList<>();

    public VehicleRepositoryImpl() throws IOException {
        loadDataBase();
    }
    @Override
    public List<Vehicle> findAll() {
        return listOfVehicles;
    }

    @Override
    public Optional<Vehicle> findById(Long id) {
        return listOfVehicles.stream().filter(v -> v.getId().equals(id)).findFirst();
    }

    @Override
    public List<Vehicle> findVehicles(String color, int year){
        System.out.printf("recebi uma request: %s %s",color, year);
        return listOfVehicles.stream()
                .filter(v -> color.isEmpty() || v.getColor().equalsIgnoreCase(color))
                .filter(v -> year == 0 || v.getYear() == year)
                .toList();
    }

    @Override
    public List<Vehicle> findVehicles(String brand, int start_date, int end_date) {
        return listOfVehicles.stream()
                .filter(v -> v.getBrand().equalsIgnoreCase(brand) && v.getYear() >= start_date && v.getYear() <= end_date)
                .toList();
    }

    @Override
    public boolean deleteVehicle(Long id) {
        return listOfVehicles.removeIf(v -> v.getId() == id);
    }

    @Override
    public boolean addVehicle(Vehicle vehicle) {
        return listOfVehicles.add(vehicle);
    }

    @Override
    public double meanSpeedByBrand(String brand) throws NotFoundException {
        return listOfVehicles.stream()
                .filter(v -> v.getBrand().equalsIgnoreCase(brand))
                .mapToInt(v -> Integer.parseInt(v.getMax_speed()))
                .average()
                .orElseThrow(() -> new InvalidVehicleException("Nenhuma marca encontrada!"));
    }

    @Override
    public List<Vehicle> bulkInsert(List<Vehicle> vehicles) {

       var response = listOfVehicles.addAll(vehicles);
        if (response) {
            return listOfVehicles;
        }
        return Collections.emptyList();
    }

    @Override
    public List<Long> getDuplicatedVehiclesIds(List<Vehicle> vehicles) {
        return  listOfVehicles.stream()
                        .filter(v -> vehicles.contains(v))
                        .map(Vehicle::getId)
                        .collect(Collectors.toList());
    }

    @Override
    public void updateSpeed(Long id, String speed) {
        listOfVehicles.stream().forEach(v -> {
            if (v.getId().equals(id)) {
                v.setMax_speed(speed);
            }
        });
    }

    @Override
    public List<Vehicle> getVehiclesByFuel(String type) {
        return listOfVehicles.stream()
                .filter(v -> v.getFuel_type().equalsIgnoreCase(type))
                .toList();
    }

    private void loadDataBase() throws IOException {
        File file;
        ObjectMapper objectMapper = new ObjectMapper();
        List<Vehicle> vehicles ;

        file= ResourceUtils.getFile("classpath:vehicles_100.json");
        vehicles= objectMapper.readValue(file,new TypeReference<List<Vehicle>>(){});

        listOfVehicles = vehicles;
    }
}
