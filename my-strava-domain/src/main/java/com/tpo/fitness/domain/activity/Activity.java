package com.tpo.fitness.domain.activity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Activity {

    private Long id;
    private String athleteId;
    private String name;
    private String externalId;
    private Float distance;
    private Integer duration;
    private Float elevation;
    private String type;
    private Date startDate;
    private Boolean commute;
    private Boolean manual;
    private Long calories;

    @Override
    public String toString() {
        return name;
    }
}