package com.tpo.fitness.domain.summary;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ActivitiesSummary {

    private Integer month;
    private Integer year;
    private Long calories;
    private Float distance;
    private LocalDateTime dateTime;

    public ActivitiesSummary() {
        distance = 0F;
        calories = 0L;
    }
}
