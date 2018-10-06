package com.tpo.fitme.strava.client.rest.athlete;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class StravaGear {
    private String id;
    private boolean primary;
    private String name;
    private Float distance;
    @JsonProperty("brand_name")
    private String brandName;
    @JsonProperty("model_name")
    private String modelName;
    @JsonProperty("frame_type")
    private String frameType;
    private String description;
    @JsonProperty("resource_state")
    private Integer resourceState;
    @JsonProperty("athlete_id")
    private String athleteId;
}