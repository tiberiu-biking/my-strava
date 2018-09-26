package com.tpo.fitme.strava.client.rest.athlete;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class StravaGear {
    private String id;
    private boolean primary;
    private String name;
    private Float distance;
    private String brandName;
    private String modelName;
    private String frameType;
    private String description;
    private Integer resourceState;
    private String athleteId;
}