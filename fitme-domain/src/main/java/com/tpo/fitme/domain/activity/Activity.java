package com.tpo.fitme.domain.activity;

import com.tpo.fitme.domain.Sport;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class Activity {

    private Long id;
    private Long athleteId;
    private String name;
    private String externalId;
    private Float distance;
    private Long duration;
    private Float elevation;
    private Sport sport;
    private LocalDateTime startDate;
    private Boolean commute;
    private Boolean manual;
    private Long calories;
}