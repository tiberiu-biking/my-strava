package com.tpo.fitness.service.athlete.activity;

import com.tpo.fitness.domain.Athlete;
import com.tpo.fitness.providers.api.service.AthleteRestClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Tiberiu on 25/10/15.
 */
@Slf4j
@Service
public class UIAthleteService implements AthleteService {

    private final AthleteRestClient stravaAthleteRestClient;

    @Autowired
    public UIAthleteService(AthleteRestClient stravaAthleteRestClient) {
        this.stravaAthleteRestClient = stravaAthleteRestClient;
    }

    @Override
    public Athlete getAthlete() {
        return stravaAthleteRestClient.getAthlete();
    }

    @Override
    public Athlete authenticate(String userName, String password) {
        return stravaAthleteRestClient.getAthlete();
    }
}
