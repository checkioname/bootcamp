package com.bootcampW22.EjercicioGlobal.service;

import com.bootcampW22.EjercicioGlobal.dto.VehicleDto;
import com.bootcampW22.EjercicioGlobal.entity.Vehicle;
import com.bootcampW22.EjercicioGlobal.exception.NotFoundException;
import com.bootcampW22.EjercicioGlobal.repository.IVehicleRepository;
import com.bootcampW22.EjercicioGlobal.repository.VehicleRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
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
    public List<VehicleDto> findVehicles(String color, int year) {
        List<Vehicle> vehicleList = vehicleRepository.findVehicles(color, year);
        if (vehicleList.isEmpty()) {
            throw new NotFoundException("No vehicle was found with color: " + color + " and year: " + year);
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
            throw new InvalidParameterException("O veículo fornecido é inválido: " + String.join(", ", validationErrors));
        }
        try {
            var vehicleDomain = convertDtoToDomain(vehicle);

            var exists = vehicleRepository.findById(vehicle.id());
            if (exists.isEmpty()) {
                return vehicleRepository.addVehicle(vehicleDomain);
            }
            throw new KeyAlreadyExistsException("Chave ja duplicada!");
        } catch (Exception e) {
            throw new InvalidParameterException("O veículo fornecido é inválido");
        }
    }

    private List<String> validateVehicle(VehicleDto vehicle) {
        List<String> errors = new ArrayList<>();
        if (vehicle == null) {
                errors.add("O veiculo nao pode ser nulo");
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
