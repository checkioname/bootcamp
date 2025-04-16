package com.bootcampW22.EjercicioGlobal.controller;

import com.bootcampW22.EjercicioGlobal.dto.VehicleDto;
import com.bootcampW22.EjercicioGlobal.exception.NotFoundException;
import com.bootcampW22.EjercicioGlobal.service.IVehicleService;
import com.bootcampW22.EjercicioGlobal.service.VehicleServiceImpl;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.security.InvalidParameterException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
public class VehicleController {

    IVehicleService vehicleService;

    public VehicleController(VehicleServiceImpl vehicleService){
        this.vehicleService = vehicleService;
    }

    @GetMapping("/vehicles")
    public ResponseEntity<?> getVehicles(){
        return new ResponseEntity<>(vehicleService.searchAllVehicles(), HttpStatus.OK);
    }

    @GetMapping("/vehicles/search")
    public ResponseEntity<?> getVehicles(@RequestParam String color, @RequestParam int year) {
        try {
            var response = vehicleService.findVehicles(color, year);
            return ResponseEntity.ok(response);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonList("Houve um erro interno"));
        }
    }

    @GetMapping("/vehicles/brandc/{brand}/between/{start_date}/{end_date}")
    public ResponseEntity<?> getVehiclesByBrandYear(@PathVariable String brand, @PathVariable int start_date, @PathVariable int end_date) {
        try {
            var response = vehicleService.findVehicles(brand, start_date, end_date);
            return ResponseEntity.ok(response);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonList("Houve um erro interno"));
        }
    }

    @GetMapping("/vehicles/average_speed/brand/{brand}")
    public ResponseEntity<?> getBrandMeanSpeed(@PathVariable String brand) {
        try {
            return ResponseEntity.ok(vehicleService.getBrandMeanSpeed(brand));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Houve um erro interno");
        }
    }

    @PostMapping("vehicles/batch")
    public ResponseEntity<?> bulkInsert(@RequestBody List<VehicleDto> vehicles) {
        try {
            return ResponseEntity.ok(vehicleService.bulkInsert(vehicles));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ouve um erro no servidor");
        }
    }


    @DeleteMapping("/vehicles/{id}")
    public ResponseEntity<?> deleteVehicle(@PathVariable String id) {
        try {
            Long vehicleId = Long.parseLong(id);
            System.out.println(vehicleId);
            vehicleService.deleteVehicle(vehicleId);
            return ResponseEntity.noContent().build();
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao deletar veículo: " + e.getMessage());
        }
    }

    @PostMapping("/vehicles")
    public ResponseEntity<?> createVehicle(@RequestBody VehicleDto vehicle) {
        try {
            var response = vehicleService.addVehicle(vehicle);
        } catch (InvalidParameterException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (KeyAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Houve um erro interno no servidor");
        }
        return null;
    }




}
