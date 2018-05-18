package com.tpo.fitness.domain.activity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Activity {

    private String id;
    private String name;
    private String externalId;
    private Float distance;
    private Integer duration;
    private Float elevation;
    private String type;
    private Date startDate;
    private Boolean commute;
    private Boolean manual;
    private Float calories;

    @Override
    public String toString() {
        return name;
    }
}