package com.tpo.fitme.strava.client.rest.athlete;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tpo.fitme.domain.Club;
import com.tpo.fitme.domain.Gear;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by Tiberiu on 21/10/15.
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
class StravaAthlete {

    private String id;

    @JsonProperty("resource_state")
    private String resourceState;

    private String firstName;

    private String lastName;

    @JsonProperty("profile_medium")
    private String profileMedium;

    private String profile;

    private String city;

    private String state;

    private String sex;

    private String friend;

    private String follower;

    private boolean premium;

    private String email;

    private int ftp;

    private float weight;

    private List<Club> clubs;

    private List<Gear> bikes;

    private List<Gear> shoes;

    private String token;

}
