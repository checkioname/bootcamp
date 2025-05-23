package com.bootcampW22.EjercicioGlobal.controller;

import com.bootcampW22.EjercicioGlobal.dto.VehicleDto;
import com.bootcampW22.EjercicioGlobal.service.IVehicleService;
import com.bootcampW22.EjercicioGlobal.service.VehicleServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    private final IVehicleService vehicleService;

    public VehicleController(VehicleServiceImpl vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping()
    public ResponseEntity<?> getVehicles(@RequestParam(required = false) Optional<String> color, @RequestParam(required = false) Optional<Integer> year) {
        return new ResponseEntity<>(vehicleService.findVehicles(color, year), HttpStatus.OK);
    }

    @GetMapping("/brand/{brand}/between/{start_date}/{end_date}")
    public ResponseEntity<?> getVehiclesByBrandYear(@PathVariable String brand, @PathVariable int start_date, @PathVariable int end_date) {
        return ResponseEntity.ok(vehicleService.findVehicles(brand, start_date, end_date));
   }

    @GetMapping("/average_speed/brand/{brand}")
    public ResponseEntity<Double> getBrandMeanSpeed(@PathVariable String brand) {
        return ResponseEntity.ok(vehicleService.getBrandMeanSpeed(brand));
    }

    @PostMapping("/batch")
    public ResponseEntity<List<VehicleDto>> bulkInsert(@RequestBody List<VehicleDto> vehicles) {
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}/update_speed")
    public ResponseEntity<String> updateSpeed(@PathVariable long id, @RequestParam String speed) {
        return ResponseEntity.ok(vehicleService.updateSpeed(id, speed));
    }

    @GetMapping("/fuel_type/{type}")
    public ResponseEntity<List<VehicleDto>> vehiclesByFuel(@PathVariable String type) {
        return ResponseEntity.ok(vehicleService.getVehiclesByFuel(type));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        vehicleService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/transmission/{type}")
    public ResponseEntity<List<VehicleDto>> getVehicleByTransmission(@PathVariable String type) {
        return ResponseEntity.ok(vehicleService.getVehicleByTransmission(type));
    }

    @PutMapping("/{id}/update_fuel")
    public ResponseEntity updateFuelType(@PathVariable Long id, @RequestParam String type) {
        vehicleService.updateFuelType(id, type);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/average_capacity/brand/{brand}")
    public ResponseEntity<Double> getAverageBrandCapacity(@PathVariable String brand) {
        return ResponseEntity.ok(vehicleService.getAverageBrandCapacity(brand));
    }

    @PostMapping()
    public ResponseEntity<?> createVehicle(@RequestBody VehicleDto vehicle) {
        return ResponseEntity.ok(vehicleService.addVehicle(vehicle));
    }
}