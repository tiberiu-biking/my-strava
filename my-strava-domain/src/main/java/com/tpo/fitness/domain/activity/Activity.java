package com.tpo.fitness.domain.activity;

import com.tpo.fitness.domain.Sport;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class Activity {

    private Long id;
    private String athleteId;
    private String name;
    private String externalId;
    private Float distance;
    private Integer duration;
    private Float elevation;
    private Sport sport;
    private LocalDateTime startDate;
    private Boolean commute;
    private Boolean manual;
    private Long calories;
}