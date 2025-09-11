package com.project.Restaurant.services;

import com.project.Restaurant.domain.RestaurantCreateUpdateRequest;
import com.project.Restaurant.domain.entities.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface RestaurantService {
    Restaurant createRestaurant(RestaurantCreateUpdateRequest request);

    Page<Restaurant> searchRestaurants(
            String query,
            Float minRating,
            Float latitude,
            Float longitude,
            Float radius,
            Pageable pageable
    );

    Optional<Restaurant> getRestaurant(String id);
    Restaurant updateRestaurant(String id, RestaurantCreateUpdateRequest restaurant);
    void deleteRestaurant(String id);


}
