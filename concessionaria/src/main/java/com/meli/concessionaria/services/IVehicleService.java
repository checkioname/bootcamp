package com.meli.concessionaria.services;

import com.meli.concessionaria.controllers.request.VehicleRequest;
import com.meli.concessionaria.controllers.responses.CarDetailsResponse;

import java.util.List;

public interface IVehicleService {

   public VehicleRequest addVehicle(VehicleRequest vehicleRequest);
   public List<CarDetailsResponse> getVehicles(String color, int year);
}
