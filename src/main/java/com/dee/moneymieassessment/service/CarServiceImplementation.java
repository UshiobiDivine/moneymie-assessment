package com.dee.moneymieassessment.service;

import com.dee.moneymieassessment.exceptions.BadRequestException;
import com.dee.moneymieassessment.model.Car;
import com.dee.moneymieassessment.payload.request.CreateCarRequest;
import com.dee.moneymieassessment.payload.request.FilterRequest;
import com.dee.moneymieassessment.payload.response.ApiResponse;
import com.dee.moneymieassessment.util.SortByParams;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarServiceImplementation implements CarService {
    private static final Logger logger = LoggerFactory.getLogger(CarServiceImplementation.class);
    @Autowired
    ObjectMapper objectMapper;

    @Override
    public List<Car> getAll() throws IOException {
        return objectMapper.readValue(new File("src/main/resources/cars-large.json"), new TypeReference<List<Car>>() {
        });
    }

    @Override
    public Car getCar(String vin) throws IOException {
        return getAll().stream().filter(car -> car.getVin().equals(vin)).findFirst()
                .orElseThrow(() -> new BadRequestException("Car not found"));
    }

    @Override
    public List<Car> getAllCarsByBrand(String brand) throws IOException {
        return getAll().stream().filter(car -> car.getBrand().equalsIgnoreCase(brand)).collect(Collectors.toList());
    }

    @Override
    public List<Car> filterCars(FilterRequest filterRequest) throws IOException {
        if (filterRequest != null) {
            List<Car> cars = getAll();
            cars.sort(new SortByParams(filterRequest));
            return cars;
        }
        return Collections.emptyList();
    }

    @Override
    public ApiResponse addCar(CreateCarRequest carRequest) throws IOException {

        if (carRequest != null) {
            List<Car> cars = getAll();

            if (cars.stream().anyMatch(car -> car.getVin().equals(carRequest.getVin()))) {
                return new ApiResponse(false, 400, "Car with vin already exist");
            }

            Car car = new Car();
            car.setBrand(carRequest.getBrand());
            car.setColor(carRequest.getColor());
            car.setVin(carRequest.getVin());
            car.setYear(carRequest.getYear());
            cars.add(car);
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File("src/main/resources/cars-large.json"), cars);
            return new ApiResponse(true, 200, "Car details added successfully " + car);
        }
        return new ApiResponse(false, 500, "Error adding car details");
    }
}
