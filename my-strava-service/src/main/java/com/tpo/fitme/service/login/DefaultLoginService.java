package com.tpo.fitme.service.login;

import com.tpo.fitme.domain.Athlete;
import com.tpo.fitme.strava.client.rest.AthleteRestClient;
import com.tpo.strava.persistence.service.AthleteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Tiberiu on 25/10/15.
 */
@Slf4j
@Service
public class DefaultLoginService implements LoginService {

    private final AthleteRestClient stravaAthleteRestClient;
    private final AthleteService athleteService;

    @Autowired
    public DefaultLoginService(AthleteRestClient athleteRestClient, AthleteService athleteService) {
        this.stravaAthleteRestClient = athleteRestClient;
        this.athleteService = athleteService;
    }

    @Override
    public Athlete login(String authCode) {
        Athlete athlete = stravaAthleteRestClient.getAthlete(authCode);
        if (athleteService.findOne(athlete.getId()) != null) {
            athleteService.updateAuthToken(athlete);
        } else {
            athleteService.save(athlete);
        }
        return athlete;
    }
}
