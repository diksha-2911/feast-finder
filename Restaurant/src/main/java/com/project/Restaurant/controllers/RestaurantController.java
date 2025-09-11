package com.project.Restaurant.controllers;

import com.project.Restaurant.domain.RestaurantCreateUpdateRequest;
import com.project.Restaurant.domain.dtos.RestaurantCreateUpdateRequestDto;
import com.project.Restaurant.domain.dtos.RestaurantDto;
import com.project.Restaurant.domain.dtos.RestaurantSummaryDto;
import com.project.Restaurant.domain.entities.Restaurant;
import com.project.Restaurant.mappers.RestaurantMapper;
import com.project.Restaurant.services.RestaurantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/restaurants")
@RequiredArgsConstructor
public class RestaurantController {
    private final RestaurantService restaurantService;
    private final RestaurantMapper restaurantMapper;

    @PostMapping
    public ResponseEntity<RestaurantDto> createRestaurant(
            @Valid @RequestBody RestaurantCreateUpdateRequestDto request
            ){
        RestaurantCreateUpdateRequest restaurantCreateUpdateRequest = restaurantMapper.toRestaurantCreateUpdateRequest(request);

        Restaurant restaurant = restaurantService.createRestaurant(restaurantCreateUpdateRequest);
        RestaurantDto createdRestaurantDto = restaurantMapper.toRestaurantDto(restaurant);

        return ResponseEntity.ok(createdRestaurantDto);
    }

    @GetMapping
    public Page<RestaurantSummaryDto> searchRestaurants(
            @RequestParam(required = false) String q,
            @RequestParam(required = false) Float minRating,
            @RequestParam(required = false) Float latitude,
            @RequestParam(required = false) Float longitude,
            @RequestParam(required = false) Float radius,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "20") int size) {
        Page<Restaurant> searchResult = restaurantService.searchRestaurants(
                q,
                minRating,
                latitude,
                longitude,
                radius,
                PageRequest.of(page - 1, size)
        );
        return searchResult.map(restaurantMapper::toSummaryDto);
    }

    @GetMapping("/{restaurantId}")
    public ResponseEntity<RestaurantDto> getRestaurant(@PathVariable String restaurantId) {
        return restaurantService.getRestaurant(restaurantId)
                .map(restaurant -> ResponseEntity.ok(restaurantMapper.toRestaurantDto(restaurant)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{restaurantId}")
    public ResponseEntity<RestaurantDto> updateRestaurant(
            @PathVariable String restaurantId,
            @Valid @RequestBody RestaurantCreateUpdateRequestDto restaurantCreateUpdateRequestDto) {
        // Convert DTO to domain object
        RestaurantCreateUpdateRequest restaurantCreateUpdateRequest =
                restaurantMapper.toRestaurantCreateUpdateRequest(restaurantCreateUpdateRequestDto);
        // Update the restaurant
        Restaurant updated = restaurantService.updateRestaurant(restaurantId, restaurantCreateUpdateRequest);
        // Convert and return the updated restaurant
        return ResponseEntity.ok(restaurantMapper.toRestaurantDto(updated));
    }

    @DeleteMapping("/{restaurantId}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable String restaurantId) {
        restaurantService.deleteRestaurant(restaurantId);
        return ResponseEntity.noContent().build();
    }


}
