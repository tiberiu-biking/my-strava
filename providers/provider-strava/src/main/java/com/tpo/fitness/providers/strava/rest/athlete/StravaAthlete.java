package com.tpo.fitness.providers.strava.rest.athlete;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
public class StravaAthlete {

    private String id;
    private String resource_state;
    private String firstname;
    private String lastname;
    private String profile_medium;
    private String profile;
    private String city;
    private String state;
    private String sex;
    private String friend;
    private String follower;
    private boolean premium;
    private String created_at;
    private String updated_at;
    private boolean approve_followers;
    private int follower_count;
    private int friend_count;
    private int mutual_friend_count;
    private String date_preference;
    private String measurement_preference;
    private String email;
    private int ftp;
    private float weight;
    private List<Club> clubs;
    private List<Gear> bikes;
    private List<Gear> shoes;
    private String token;
}