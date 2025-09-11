package com.project.Restaurant.domain.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TimeRangeDto {

    @NotBlank(message = "Opening time must be provided")
    @Pattern(regexp = "^([01]?[0-9]|2[0-3]):[0-5][0-9]$")
    private String openTime;

//    @NotBlank(message = "Closing time must be provided")
//    @Pattern(regexp = "^([01]?[0-9]|2[0-3]):[0-5][0-9]$")
    private String closeTime;

//    public TimeRangeDto(String openTime, String closeTime) {
//        this.openTime = openTime;
//        this.closeTime = closeTime;
//    }
//
//    public TimeRangeDto() {
//    }
//
//    public String getOpenTime() {
//        return openTime;
//    }
//
//    public void setOpenTime(String openTime) {
//        this.openTime = openTime;
//    }
//
//    public String getCloseTime() {
//        return closeTime;
//    }
//
//    public void setCloseTime(String closeTime) {
//        this.closeTime = closeTime;
//    }
}
