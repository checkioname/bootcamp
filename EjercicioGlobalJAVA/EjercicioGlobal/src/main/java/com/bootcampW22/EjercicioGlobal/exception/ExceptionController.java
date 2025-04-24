package com.bootcampW22.EjercicioGlobal.exception;

import com.bootcampW22.EjercicioGlobal.controller.VehicleController;
import com.bootcampW22.EjercicioGlobal.dto.ExceptionDto;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@ControllerAdvice(assignableTypes=VehicleController.class)
public class ExceptionController {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> notFound(NotFoundException e){
        ExceptionDto exceptionDto = new ExceptionDto(e.getMessage());
        return new ResponseEntity<>(exceptionDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateVehicleException.class)
    public ResponseEntity<?> duplicateVehicle(DuplicateVehicleException e){
        ExceptionDto exceptionDto = new ExceptionDto(e.getMessage());
        return new ResponseEntity<>(exceptionDto, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(InvalidVehicleException.class)
    public ResponseEntity<?> invalidVehicle(InvalidVehicleException e){
        ExceptionDto exceptionDto = new ExceptionDto(e.getMessage());
        return new ResponseEntity<>(exceptionDto, HttpStatus.BAD_REQUEST);
    }

}
