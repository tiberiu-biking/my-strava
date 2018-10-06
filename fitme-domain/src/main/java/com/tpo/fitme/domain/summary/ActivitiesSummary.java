package com.tpo.fitme.domain.summary;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ActivitiesSummary {

    private Integer month;
    private Integer year;
    private Long calories;
    private Float distance;
    private Float elevation;
    private LocalDateTime dateTime;

    public ActivitiesSummary() {
        distance = 0F;
        calories = 0L;
        elevation = 0F;
    }
}
