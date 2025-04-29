package com.bootcampW22.EjercicioGlobal.service;

import com.bootcampW22.EjercicioGlobal.dto.VehicleDto;
import com.bootcampW22.EjercicioGlobal.entity.Vehicle;
import com.bootcampW22.EjercicioGlobal.exception.DuplicateVehicleException;
import com.bootcampW22.EjercicioGlobal.exception.InvalidFuelType;
import com.bootcampW22.EjercicioGlobal.exception.InvalidVehicleException;
import com.bootcampW22.EjercicioGlobal.exception.NotFoundException;
import com.bootcampW22.EjercicioGlobal.repository.IVehicleRepository;
import com.bootcampW22.EjercicioGlobal.repository.VehicleRepositoryImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VehicleServiceImpl implements IVehicleService {

    private final IVehicleRepository vehicleRepository;

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
        List<Vehicle> vehicles = vehicleRepository.findVehicles(brand, start_date, end_date);
        if (vehicles.isEmpty()) {
            throw new NotFoundException("No vehicle was found!");
        }

        return vehicles.stream()
                .map(this::convertVehicleToDto)
                .toList();
    }


    @Override
    public double getBrandMeanSpeed(String brand) throws NotFoundException {
       return vehicleRepository.getVehiclesByBrand(brand).stream()
               .mapToInt(v -> Integer.parseInt(v.getMax_speed()))
               .average()
               .orElseThrow(() -> new InvalidVehicleException("Nenhuma marca encontrada!"));
    }

    @Override
    public void bulkInsert(List<VehicleDto> vehiclesDtos) {
        List<Vehicle> vehicles = vehiclesDtos.stream().map(this::convertDtoToDomain).toList();
        List<Long> existingVehicles = vehicleRepository.getDuplicatedVehiclesIds(vehicles);
        if (!existingVehicles.isEmpty()) {
            throw new DuplicateVehicleException("Esses veiculos ja foram adicionados: " + existingVehicles);
        }

        vehicleRepository.bulkInsert(vehicles);
//        return vehiclesDtos;
//                response.stream().map(this::convertVehicleToDto).toList();
    }

    @Override
    public String updateSpeed(Long id, String speed) {
        double speedNumber;
        isSpeedValid(speed);

        var vehicleExist = vehicleRepository.findById(id);
        if (vehicleExist.isEmpty()) {
            throw new NotFoundException("Vehicle not found");
        }

        vehicleRepository.updateSpeed(id, speed);
        return "veiculo atualizado com sucesso";
    }

    private static void isSpeedValid(String speed) {
        double speedNumber;
        try {
            speedNumber = Double.parseDouble(speed);
        } catch (Exception e) {
           throw new InvalidVehicleException("Speed malformed");
        }

        if (speedNumber < 0) {
            throw new InvalidVehicleException("Speed must be greater or equal than 0");
        }
    }

    @Override
    public List<VehicleDto> getVehiclesByFuel(String type) {
        List<Vehicle> vehicles = vehicleRepository.getVehiclesByFuel(type);
        if (vehicles.isEmpty()) {
            throw new NotFoundException("No vehicle was found");
        }
        return vehicles.stream().map(this::convertVehicleToDto).toList();
    }

    @Override
    public void deleteById(Long id) {
        if (!isIdValid(id)) {
            throw new InvalidVehicleException("The id is malformed");
        }

        var vehicle = vehicleRepository.findById(id);
        if (vehicle.isEmpty()) {
            throw new NotFoundException("No vehicle was found");
        }

        vehicleRepository.deleteVehicle(id);
    }


    @Override
    public List<VehicleDto> getVehicleByTransmission(String type) {
        List<Vehicle> vehicles =  vehicleRepository.getVehiclesByTransmission(type);
        if (vehicles.isEmpty()) {
            throw new NotFoundException("No vehicle was found");
        }
        return vehicles.stream().map(this::convertVehicleToDto).toList();
    }

    @Override
    public void updateFuelType(Long id, String type) {
        var vehicle = vehicleRepository.findById(id);
        if (vehicle.isEmpty()) {
            throw new NotFoundException("No vehicle was found");
        }
        if (!isFuelTypeValid(type)) {
            throw new InvalidFuelType("Fuel type does not exist");
        }
        vehicleRepository.updateFuelType(id, type.toLowerCase());
    }

    @Override
    public Double getAverageBrandCapacity(String brand) {
        return vehicleRepository.getVehiclesByBrand(brand).stream()
               .mapToInt(Vehicle::getPassengers)
               .average()
               .orElseThrow(() -> new InvalidVehicleException("No vehicle was found"));
    }

    private boolean isFuelTypeValid(String type) {
        return true;
    }

    @Override
    public String addVehicle(VehicleDto vehicle) {
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
        vehicleRepository.addVehicle(vehicleDomain);
        return "Veiculo criado com sucesso!";
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
        if (!isVehicleDtoValid(v)) {
            throw new InvalidVehicleException("Vehicle data malformed");
        }

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

    private boolean isVehicleDtoValid(VehicleDto dto) {
        if (dto == null) {
            return false;
        }
        if (dto.id() < 0) {
            return false;
        }
        if (dto.length() < 0) {
            return false;
        }
        if (dto.width() < 0) {
            return false;
        }
        return true;
    }

    private boolean isIdValid(Long id) { return true;}
}
