package com.meli.concessionaria.controllers;


import com.meli.concessionaria.controllers.exceptions.VehicleAlreadyExistsException;
import com.meli.concessionaria.controllers.request.VehicleRequest;
import com.meli.concessionaria.services.IVehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicles")
public class CarController {

    @Autowired
    private IVehicleService vehicleService;

    public CarController(IVehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping()
    public ResponseEntity<?> createVehicle(@RequestBody VehicleRequest vehicleRequest) {
        try {
            VehicleRequest createdVehicle = vehicleService.addVehicle(vehicleRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdVehicle);
        } catch (VehicleAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Dados do veículo mal formatados ou incompletos.");
        }
    };


    @GetMapping
    public ResponseEntity<?> getVehicles(@RequestParam String color, @RequestParam int year) {
        try {
            var response = vehicleService.getVehicles(color, year);
            if (response.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum registro encontrado!");
            }
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Houve um erro interno no servidor");
        }
    }






}
