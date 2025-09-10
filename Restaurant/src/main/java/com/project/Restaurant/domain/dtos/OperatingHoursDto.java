package com.project.Restaurant.domain.dtos;

import com.project.Restaurant.domain.entities.TimeRange;
import jakarta.validation.Valid;

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
}
