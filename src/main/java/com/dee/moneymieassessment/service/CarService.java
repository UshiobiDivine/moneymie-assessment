package com.dee.moneymieassessment.service;

import com.dee.moneymieassessment.model.Car;
import com.dee.moneymieassessment.payload.request.CreateCarRequest;
import com.dee.moneymieassessment.payload.request.FilterRequest;
import com.dee.moneymieassessment.payload.response.ApiResponse;

import java.io.IOException;
import java.util.List;


public interface CarService {
    List<Car> getAll() throws IOException;
    Car getCar(String vin) throws IOException;
    List<Car> getAllCarsByBrand(String brand) throws IOException;
    List<Car> filterCars(FilterRequest filterRequest) throws IOException;
    ApiResponse addCar(CreateCarRequest carRequest) throws IOException;
}
