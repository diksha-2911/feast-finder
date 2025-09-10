package com.project.Restaurant.domain.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

public class AddressDto {

    @NotBlank(message = "Street number is required.")
    @Pattern(regexp =  "^[0-9]{1,5}[a-zA-Z]?$", message = "Invalid street number format")
    private String streetNo;

    @NotBlank(message = "Street number is required.")
    private String streetName;

    private String unit;

    @NotBlank(message = "Street number is required.")
    private String city;

    @NotBlank(message = "Street number is required.")
    private String state;

    @NotBlank(message = "Street number is required.")
    private String postalCode;

    @NotBlank(message = "Street number is required.")
    private String country;

}
