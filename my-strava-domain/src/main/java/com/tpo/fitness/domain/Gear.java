package com.tpo.fitness.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Tiberiu on 21/10/15.
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Gear {

    private String id;

    private boolean primary;

    private String name;

    private float distance;

    @JsonProperty("brand_name")
    private String brandName;

    @JsonProperty("model_name")
    private String modelName;

    @JsonProperty("frame_type")
    private String frameType;

    private String description;

    @JsonProperty("resource_state")
    private int resourceState;
}