package com.dee.moneymieassessment.payload.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CreateCarRequest {

    @NotNull
    @NotBlank
    private String vin;
    @NotNull
    @NotBlank
    private String brand;
    @NotNull
    @NotBlank
    private String year;
    @NotNull
    @NotBlank
    private String color;
}
