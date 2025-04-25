package com.bootcampW22.EjercicioGlobal.controller;

import com.bootcampW22.EjercicioGlobal.dto.VehicleDto;
import com.bootcampW22.EjercicioGlobal.exception.NotFoundException;
import com.bootcampW22.EjercicioGlobal.service.IVehicleService;
import com.bootcampW22.EjercicioGlobal.service.VehicleServiceImpl;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.security.InvalidParameterException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@RestController
public class VehicleController {

    private final IVehicleService vehicleService;

    public VehicleController(VehicleServiceImpl vehicleService){
        this.vehicleService = vehicleService;
    }

    @GetMapping("/vehicles")
    public ResponseEntity<?> getVehicles(@RequestParam(required = false) Optional<String> color , @RequestParam(required = false) Optional<Integer> year){
        return new ResponseEntity<>(vehicleService.findVehicles(color, year), HttpStatus.OK);
    }

    @GetMapping("/vehicles/brandc/{brand}/between/{start_date}/{end_date}")
    public ResponseEntity<?> getVehiclesByBrandYear(@PathVariable String brand, @PathVariable int start_date, @PathVariable int end_date) {
            var response = vehicleService.findVehicles(brand, start_date, end_date);
            return ResponseEntity.ok(response);
    }

    @GetMapping("/vehicles/average_speed/brand/{brand}")
    public ResponseEntity<?> getBrandMeanSpeed(@PathVariable String brand) {
            return ResponseEntity.ok(vehicleService.getBrandMeanSpeed(brand));
    }

    @PostMapping("/vehicles/batch")
    public ResponseEntity<?> bulkInsert(@RequestBody List<VehicleDto> vehicles) {
            return ResponseEntity.ok(vehicleService.bulkInsert(vehicles));
   }

    @PutMapping("/vehicles/{id}/update_speed")
    public ResponseEntity<?> updateSpeed(@PathVariable long id, @RequestParam String speed) {
            return ResponseEntity.ok(vehicleService.updateSpeed(id, speed));
   }


    @DeleteMapping("/vehicles/{id}")
    public ResponseEntity<?> deleteVehicle(@PathVariable Long id) {
            vehicleService.deleteVehicle(id);
            return ResponseEntity.noContent().build();
   }

    @PostMapping("/vehicles")
    public ResponseEntity<?> createVehicle(@RequestBody VehicleDto vehicle) {
        return ResponseEntity.ok(vehicleService.addVehicle(vehicle));
    }




}
