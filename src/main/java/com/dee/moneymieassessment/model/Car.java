package com.dee.moneymieassessment.model;

import com.dee.moneymieassessment.exceptions.BadRequestException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car {
    private String vin;
    private String brand;
    private String year;
    private String color;

    public String getParam(String param){
        switch (param){
            case "vin":
                return getVin();
            case "brand":
                return getBrand();
            case "year":
                return getYear();
            case "color":
                return getColor();
        }
        throw new BadRequestException("Invalid param specified");
    }
}
