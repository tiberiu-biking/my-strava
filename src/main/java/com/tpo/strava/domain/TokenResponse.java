package com.tpo.strava.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by Tiberiu on 21/10/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TokenResponse {
    private Athlete athlete;

    public Athlete getAthlete() {
        return athlete;
    }

    public void setAthlete(Athlete athlete) {
        this.athlete = athlete;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("athlete", athlete)
                .toString();
    }
}
