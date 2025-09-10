package com.project.Restaurant.mappers;

import com.project.Restaurant.domain.RestaurantCreateUpdateRequest;
import com.project.Restaurant.domain.dtos.GeoPointDto;
import com.project.Restaurant.domain.dtos.RestaurantCreateUpdateRequestDto;
import com.project.Restaurant.domain.dtos.RestaurantDto;
import com.project.Restaurant.domain.entities.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RestaurantMapper {

    //user will give to server --> object required
    RestaurantCreateUpdateRequest toRestaurantCreateUpdateRequest(RestaurantCreateUpdateRequestDto dto);

    //server will give to user --> dto required
    RestaurantDto toRestaurantDto(Restaurant restaurant);

    //server will give to user --> dto required
    @Mapping(target = "latitude", expression = "java(geoPoint.getLat())")
    @Mapping(target = "longitude", expression = "java(geoPoint.getLon())")
    GeoPointDto toGeoPointDto(GeoPoint geoPoint);
}
