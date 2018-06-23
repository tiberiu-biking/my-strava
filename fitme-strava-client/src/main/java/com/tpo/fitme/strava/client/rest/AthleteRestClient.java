package com.tpo.fitme.strava.client.rest;

import com.tpo.fitme.domain.Athlete;

public interface AthleteRestClient {
    Athlete getAthlete(String authCode);
}
