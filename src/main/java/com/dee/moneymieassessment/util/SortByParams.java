package com.dee.moneymieassessment.util;

import com.dee.moneymieassessment.exceptions.BadRequestException;
import com.dee.moneymieassessment.model.Car;
import com.dee.moneymieassessment.payload.request.FilterRequest;

import java.util.Comparator;

public class SortByParams implements Comparator<Car> {
    private FilterRequest filterRequest;

    public SortByParams(FilterRequest filterRequest) {
        this.filterRequest = filterRequest;
    }

    @Override
    public int compare(Car car1, Car car2) {
        if (isAscending()){
            return car1.getParam(filterRequest.getParam()).compareTo(car2.getParam(filterRequest.getParam()));
        }else if (isDescending()){
            return car2.getParam(filterRequest.getParam()).compareTo(car1.getParam(filterRequest.getParam()));
        }
        throw new BadRequestException("Invalid order specified");
    }

    private boolean isAscending(){
        return filterRequest.getOrder().equalsIgnoreCase("asc") ||
                filterRequest.getOrder().equalsIgnoreCase("ascending");
    }
    private boolean isDescending(){
        return filterRequest.getOrder().equalsIgnoreCase("desc") ||
                filterRequest.getOrder().equalsIgnoreCase("descending");
    }

}
