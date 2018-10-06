package com.tpo.fitme.strava.client.rest.actitivity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tpo.fitme.domain.Athlete;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
class StravaActivity {

    private String id;

    @JsonProperty("resource_state")
    private int resourceState;

    @JsonProperty("external_id")
    private String externalId;

    @JsonProperty("uploadId")
    private int uploadId;

    private Athlete athlete;

    private String name;

    private Float distance;

    @JsonProperty("moving_time")
    private Long movingTime;

    @JsonProperty("elapsed_time")
    private Integer elapsedTime;

    @JsonProperty("workout_type")
    private Integer workoutType;

    @JsonProperty("total_elevation_gain")
    private Float totalElevationGain;

    private String type;

    @JsonProperty("start_date")
    private Date startDate;

    @JsonProperty("start_date_local")
    private String startDateLocal;

    private String timezone;

    private boolean commute;

    private boolean manual;

    @JsonProperty("private")
    private boolean isPrivate;

    private boolean flagged;

    @JsonProperty("gear_id")
    private String gearId;

    @JsonProperty("average_speed")
    private Float averageSpeed;

    @JsonProperty("max_speed")
    private Float maxSpeed;

    @JsonProperty("averageCadence")
    private Float average_cadence;

    @JsonProperty("average_temp")
    private int averageTemp;

    @JsonProperty("average_watts")
    private Float averageWatts;

    private Float kilojoules;

    private Long calories;

    private int truncated;

    @JsonProperty("has_kudoed")
    private boolean hasKudoed;
}