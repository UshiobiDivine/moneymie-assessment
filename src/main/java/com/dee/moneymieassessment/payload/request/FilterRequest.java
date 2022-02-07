package com.dee.moneymieassessment.payload.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class FilterRequest {
    @NotNull
    @NotBlank
    private String param;
    @NotNull
    @NotBlank
    private String order;
}
