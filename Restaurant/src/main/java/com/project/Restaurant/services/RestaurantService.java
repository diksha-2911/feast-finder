package com.project.Restaurant.services;

import com.project.Restaurant.domain.RestaurantCreateUpdateRequest;
import com.project.Restaurant.domain.entities.Restaurant;

public interface RestaurantService {
    Restaurant createRestaurant(RestaurantCreateUpdateRequest request);
}
