package com.project.Restaurant.domain.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ReviewCreateUpdateRequestDto {

    @NotBlank(message = "Review content is required")
    private String content;

    @NotNull(message = "Rating is required")
    @Min(value = 1, message = "Rating must be between 1-5")
    @Max(value = 5, message = "Rating must be between 1-5")
    private Integer rating;

    private List<String> photoIds = new ArrayList<>();
}
