package com.project.Restaurant.domain;

import com.project.Restaurant.domain.entities.Address;
import com.project.Restaurant.domain.entities.OperatingHours;
import com.project.Restaurant.domain.entities.Photo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestaurantCreateUpdateRequest {
    private String name;
    private String cuisineType;
    private String contactInformation;
    private Address address;
    private OperatingHours operatingHours;
    private List<String> photoIds;
}
