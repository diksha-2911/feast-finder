package com.project.Restaurant.domain.dtos;

import com.project.Restaurant.domain.entities.TimeRange;
import jakarta.validation.Valid;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OperatingHoursDto {

    @Valid
    private TimeRangeDto monday;
    @Valid
    private TimeRangeDto tuesday;
    @Valid
    private TimeRangeDto wednesday;
    @Valid
    private TimeRangeDto thursday;
    @Valid
    private TimeRangeDto friday;
    @Valid
    private TimeRangeDto saturday;
    @Valid
    private TimeRangeDto sunday;

//    public OperatingHoursDto(TimeRangeDto monday, TimeRangeDto tuesday, TimeRangeDto wednesday, TimeRangeDto thursday, TimeRangeDto friday, TimeRangeDto saturday, TimeRangeDto sunday) {
//        this.monday = monday;
//        this.tuesday = tuesday;
//        this.wednesday = wednesday;
//        this.thursday = thursday;
//        this.friday = friday;
//        this.saturday = saturday;
//        this.sunday = sunday;
//    }
//
//    public OperatingHoursDto() {
//    }
//
//    public TimeRangeDto getMonday() {
//        return monday;
//    }
//
//    public void setMonday(TimeRangeDto monday) {
//        this.monday = monday;
//    }
//
//    public TimeRangeDto getTuesday() {
//        return tuesday;
//    }
//
//    public void setTuesday(TimeRangeDto tuesday) {
//        this.tuesday = tuesday;
//    }
//
//    public TimeRangeDto getWednesday() {
//        return wednesday;
//    }
//
//    public void setWednesday(TimeRangeDto wednesday) {
//        this.wednesday = wednesday;
//    }
//
//    public TimeRangeDto getThursday() {
//        return thursday;
//    }
//
//    public void setThursday(TimeRangeDto thursday) {
//        this.thursday = thursday;
//    }
//
//    public TimeRangeDto getFriday() {
//        return friday;
//    }
//
//    public void setFriday(TimeRangeDto friday) {
//        this.friday = friday;
//    }
//
//    public TimeRangeDto getSaturday() {
//        return saturday;
//    }
//
//    public void setSaturday(TimeRangeDto saturday) {
//        this.saturday = saturday;
//    }
//
//    public TimeRangeDto getSunday() {
//        return sunday;
//    }
//
//    public void setSunday(TimeRangeDto sunday) {
//        this.sunday = sunday;
//    }
}
