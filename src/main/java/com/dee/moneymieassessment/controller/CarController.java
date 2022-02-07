package com.dee.moneymieassessment.controller;

import com.dee.moneymieassessment.model.Car;
import com.dee.moneymieassessment.payload.request.CreateCarRequest;
import com.dee.moneymieassessment.payload.request.FilterRequest;
import com.dee.moneymieassessment.payload.response.ApiResponse;
import com.dee.moneymieassessment.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/car")
public class CarController {
    private CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Car>> getAllCars() throws IOException {
        return new ResponseEntity<>(carService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/all/{brand}")
    public ResponseEntity<List<Car>> getAllCarsByBrand(@PathVariable(name = "brand") String brand) throws IOException {
        return new ResponseEntity<>(carService.getAllCarsByBrand(brand), HttpStatus.OK);
    }

    @GetMapping("/{vin}")
    public ResponseEntity<Car> getCarByVin(@PathVariable(name = "vin") String vin) throws IOException {
        return new ResponseEntity<>(carService.getCar(vin), HttpStatus.OK);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Car>> filterCars(@Valid @RequestBody FilterRequest request) throws IOException {
        return new ResponseEntity<>(carService.filterCars(request), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<ApiResponse> addCars(@Valid @RequestBody CreateCarRequest request) throws IOException {
        ApiResponse apiResponse = carService.addCar(request);
        return new ResponseEntity<>(apiResponse, HttpStatus.valueOf(apiResponse.getHttpResponseCode()));
    }

}
