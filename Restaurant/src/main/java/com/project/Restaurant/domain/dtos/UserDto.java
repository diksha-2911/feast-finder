package com.project.Restaurant.domain.dtos;

import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

public class UserDto {


    private String id;
    private String username;
    private String firstName;
    private String familyName;

}
