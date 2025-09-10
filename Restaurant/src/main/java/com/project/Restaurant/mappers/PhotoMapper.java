package com.project.Restaurant.mappers;


import com.project.Restaurant.domain.dtos.PhotoDto;
import com.project.Restaurant.domain.entities.Photo;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PhotoMapper {

    //server is giving to user --> Dto required
    PhotoDto toDto(Photo photo);

}
