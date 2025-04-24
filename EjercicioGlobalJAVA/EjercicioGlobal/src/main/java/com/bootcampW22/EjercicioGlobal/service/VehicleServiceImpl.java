package com.bootcampW22.EjercicioGlobal.service;

import com.bootcampW22.EjercicioGlobal.dto.VehicleDto;
import com.bootcampW22.EjercicioGlobal.entity.Vehicle;
import com.bootcampW22.EjercicioGlobal.exception.DuplicateVehicleException;
import com.bootcampW22.EjercicioGlobal.exception.InvalidVehicleException;
import com.bootcampW22.EjercicioGlobal.exception.NotFoundException;
import com.bootcampW22.EjercicioGlobal.repository.IVehicleRepository;
import com.bootcampW22.EjercicioGlobal.repository.VehicleRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VehicleServiceImpl implements IVehicleService {

    @Autowired
    IVehicleRepository vehicleRepository;

    public VehicleServiceImpl(VehicleRepositoryImpl vehicleRepository){
        this.vehicleRepository = vehicleRepository;
    }
    @Override
    public List<VehicleDto> searchAllVehicles() {
        List<Vehicle> vehicleList = vehicleRepository.findAll();
        if(vehicleList.isEmpty()){
            throw new NotFoundException("No se encontró ningun auto en el sistema.");
        }
        return vehicleList.stream()
                .map(this::convertVehicleToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<VehicleDto> findVehicles(Optional<String> color, Optional<Integer> year) {
        var c = color.orElse("");
        var y = year.orElse(0);

        List<Vehicle> vehicleList = vehicleRepository.findVehicles(c, y);
        if (vehicleList.isEmpty()) {
            throw new NotFoundException("No vehicle was found with color: " + c + " and year: " + y);
        }
        return vehicleList.stream()
                .map(this::convertVehicleToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<VehicleDto> findVehicles(String brand, int start_date, int end_date) {
        List<Vehicle> vehicleList = vehicleRepository.findVehicles(brand, start_date, end_date);
        if (vehicleList.isEmpty()) {
            throw new NotFoundException("Nenhum veiculo encontrado");
        }
        return vehicleList.stream()
                .map(this::convertVehicleToDto)
                .collect(Collectors.toList());
    }

    @Override
    public double getBrandMeanSpeed(String brand) {
       return vehicleRepository.meanSpeedByBrand(brand);
    }

    @Override
    public List<VehicleDto> bulkInsert(List<VehicleDto> vehiclesDtos) {
        var vehicles = vehiclesDtos.stream().map(this::convertDtoToDomain).toList();
        var response = vehicleRepository.bulkInsert(vehicles);
        return response.stream().map(this::convertVehicleToDto).toList();
    }

    @Override
    public void updateSpeed(Long id, String speed) {
        vehicleRepository.updateSpeed(id, speed);
    }


    @Override
    public void deleteVehicle(Long id) {
        if (isIdValid(id)) {
            var vehicle = vehicleRepository.findById(id); // Método findById retornando Car ou null
            if (vehicle.isEmpty()) {
                throw new NotFoundException(("Veículo não encontrado com ID: " + id));
            }
            vehicleRepository.deleteVehicle(vehicle.get().getId());
            return;
        }
        throw new InvalidParameterException("O parametro fornecido é inválido");
    }

    @Override
    public boolean addVehicle(VehicleDto vehicle) {
        List<String> validationErrors = validateVehicle(vehicle);
        if (!validationErrors.isEmpty()) {
            System.out.println("Tentativa de adição de veículo falhou: " + String.join(", ", validationErrors));
            throw new InvalidVehicleException("O veículo fornecido é inválido: " + String.join(", ", validationErrors));
        }
        var vehicleDomain = convertDtoToDomain(vehicle);

        var exists = vehicleRepository.findById(vehicle.id());
        if (exists.isPresent()) {
            throw new DuplicateVehicleException("O veículo ja existe!");
        }
        return vehicleRepository.addVehicle(vehicleDomain);
    }

    private List<String> validateVehicle(VehicleDto vehicle) {
        List<String> errors = new ArrayList<>();
        if (vehicle == null) {
                errors.add("O veiculo nao pode ser nulo");
        }
        if (vehicle.brand() == null || vehicle.brand().isEmpty()) {
            errors.add("O veiculo precisa ter uma marca");
        }
        // ... (adicionar mais validacoes)
        return errors;
    }

    private Vehicle convertDtoToDomain(VehicleDto v) {
        return new Vehicle(
               v.id(),
               v.brand(),
               v.model(),
               v.registration(),
               v.color(),
               v.year(),
               v.max_speed(),
               v.passengers(),
               v.fuel_type(),
               v.transmission(),
               v.length(),
               v.width(),
               v.weight()
        );
    }

    private VehicleDto convertVehicleToDto(Vehicle v){
        return new VehicleDto(
                v.getId(),
                v.getBrand(),
                v.getModel(),
                v.getRegistration(),
                v.getColor(),
                v.getYear(),
                v.getMax_speed(),
                v.getPassengers(),
                v.getFuel_type(),
                v.getTransmission(),
                v.getLength(),
                v.getWidth(),
                v.getWeight());
    }


    private boolean isIdValid(Long id) { return true;}
}
