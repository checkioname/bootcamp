package com.meli.concessionaria.services;

import com.meli.concessionaria.controllers.exceptions.VehicleAlreadyExistsException;
import com.meli.concessionaria.controllers.request.VehicleRequest;
import com.meli.concessionaria.controllers.responses.CarDetailsResponse;
import com.meli.concessionaria.models.Car;
import com.meli.concessionaria.repositories.ICarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService implements IVehicleService {

    @Autowired
    private ICarRepository carRepository;

    public VehicleService(ICarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public VehicleRequest addVehicle(VehicleRequest vehicleRequest) {
        Car car = vehicleRequest.toCarModel();
        try {
            carRepository.addVehicle(car);
            return vehicleRequest;
        } catch (VehicleAlreadyExistsException e) {
            throw new RuntimeException("Veículo já existente.", e);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao adicionar veículo: " + e.getMessage(), e);
        }
    }

    @Override
    public List<CarDetailsResponse> getVehicles(String color, int year) {
        var carList = carRepository.getVehicles(color, year);
        var responseList = carList.stream()
                .map(c -> new CarDetailsResponse(
                        c.getBrand(),
                        c.getModel(),
                        c.getManufacuringDate(),
                        c.getNumberOfKilometers(),
                        c.getDoors(),
                        c.getPrice(),
                        c.getCurrency(),
                        c.getCountOfOwners()
                )).toList();
        return responseList;
    }

}
