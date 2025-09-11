package com.project.Restaurant.services.impl;

import com.project.Restaurant.domain.GeoLocation;
import com.project.Restaurant.domain.entities.Address;
import com.project.Restaurant.services.GeoLocationService;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class GeoLocationServiceImpl implements GeoLocationService {


    private static final float MIN_LATITUDE = 8.069f;
    private static final float MAX_LATITUDE = 37.09f;
    private static final float MIN_LONGITUDE = 68.11f;
    private static final float MAX_LONGITUDE = 97.40f;

    @Override
    public GeoLocation geoLocate(Address address) {
        Random random = new Random();
        double latitude = MIN_LATITUDE + random.nextDouble() * (MAX_LATITUDE - MIN_LATITUDE);
        double longitude = MIN_LONGITUDE + random.nextDouble() * (MAX_LONGITUDE - MIN_LONGITUDE);

        return GeoLocation.builder()
                .latitude(latitude)
                .longitude(longitude)
                .build();
    }
}
