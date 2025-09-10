package com.project.Restaurant.domain.dtos;

import com.project.Restaurant.domain.entities.Address;
import com.project.Restaurant.domain.entities.OperatingHours;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

public class RestaurantCreateUpdateRequestDto {

    @NotBlank(message="Name must be provided")
    private String name;

    @NotBlank(message="Cuisine type must be provided")
    private String cuisineType;

    @NotBlank(message="Contact Information must be provided")
    private String contactInformation;

    @Valid
    private AddressDto address;

    @Valid
    private OperatingHoursDto operatingHours;

    @Size(min = 1, message = "At least one photo ID is required")
    private List<String> photoIds;
}
