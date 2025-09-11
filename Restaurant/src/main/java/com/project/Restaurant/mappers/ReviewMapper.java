package com.project.Restaurant.mappers;


import com.project.Restaurant.domain.ReviewCreateUpdateRequest;
import com.project.Restaurant.domain.dtos.ReviewCreateUpdateRequestDto;
import com.project.Restaurant.domain.dtos.ReviewDto;
import com.project.Restaurant.domain.entities.Review;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ReviewMapper {

    ReviewCreateUpdateRequest toReviewCreateUpdateRequest(ReviewCreateUpdateRequestDto dto);

    ReviewDto toDto(Review review);
}
