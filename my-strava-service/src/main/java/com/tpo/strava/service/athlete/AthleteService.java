package com.tpo.strava.service.athlete;

import com.tpo.strava.service.domain.Athlete;

/**
 * Created by Tiberiu on 25/10/15.
 */
public interface AthleteService {

    Athlete getAthlete(String authCode);
}
