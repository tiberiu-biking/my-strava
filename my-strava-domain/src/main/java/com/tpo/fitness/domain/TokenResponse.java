package com.tpo.fitness.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by Tiberiu on 21/10/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TokenResponse {
    private Athlete athlete;
    @JsonProperty("access_token")
    private String accessToken;

    public Athlete getAthlete() {
        return athlete;
    }

    public void setAthlete(Athlete athlete) {
        this.athlete = athlete;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("athlete", athlete)
                .toString();
    }
}
