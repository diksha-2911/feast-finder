package com.project.Restaurant.services;

import com.project.Restaurant.domain.GeoLocation;
import com.project.Restaurant.domain.entities.Address;

public interface GeoLocationService {
    GeoLocation geoLocate(Address address);
}
