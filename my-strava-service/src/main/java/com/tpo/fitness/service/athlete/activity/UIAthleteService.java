package com.tpo.fitness.service.athlete.activity;

import com.tpo.fitme.domain.Athlete;
import com.tpo.fitness.providers.api.service.AthleteRestClient;
import com.tpo.fitness.service.sync.Synchronizer;
import com.tpo.strava.persistence.service.repository.repository.AthleteRepository;
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
    private final AthleteRepository athleteRepository;
    private final Synchronizer      synchronizer;

    @Autowired
    public UIAthleteService(AthleteRestClient stravaAthleteRestClient, AthleteRepository athleteRepository, Synchronizer synchronizer) {
        this.stravaAthleteRestClient = stravaAthleteRestClient;
        this.athleteRepository = athleteRepository;
        this.synchronizer = synchronizer;
    }

    @Override
    public Athlete login(String authCode) {
        Athlete athlete = stravaAthleteRestClient.getAthlete(authCode);
        if (athleteRepository.exists(athlete.getId())) {
            athleteRepository.updateAuthToken(athlete);
        } else {
            athleteRepository.save(athlete);
        }
        synchronizer.sync(athlete);
        return athlete;
    }
}
