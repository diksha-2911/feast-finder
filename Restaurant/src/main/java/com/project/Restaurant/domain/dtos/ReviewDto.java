package com.project.Restaurant.domain.dtos;

import com.project.Restaurant.domain.entities.Photo;
import com.project.Restaurant.domain.entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewDto {

    private String id;
    private String content;

    private Integer rating;

    private LocalDateTime datePosted;

    private LocalDateTime dateEdited;

    private List<PhotoDto> photos = new ArrayList<>();

    private User writtenBy;
}
