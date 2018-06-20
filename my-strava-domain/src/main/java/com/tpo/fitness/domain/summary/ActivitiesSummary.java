package com.tpo.fitness.domain.summary;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Created by Tiberiu on 28/10/15.
 */
@Getter
@Setter
@EqualsAndHashCode
public class ActivitiesSummary {

    private Long calories;
    private Float distance;
    private Integer month;
    private Integer year;
    private LocalDateTime dateTime;

    public ActivitiesSummary() {
        distance = 0F;
        calories = 0L;
    }

    public String getPeriod() {
        return month + "/" + year;
    }
}
