package com.tpo.fitme.service.sync;

import com.tpo.fitme.domain.Athlete;
import com.tpo.fitme.strava.client.rest.AthleteRestClient;
import com.tpo.strava.persistence.service.GearService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Tiberiu
 * @since 17.07.18
 */
@Slf4j
public class AthleteSynchronizer implements Synchronizer {

    private final AthleteRestClient athleteRestClient;
    private final GearService gearService;

    @Autowired
    public AthleteSynchronizer(AthleteRestClient athleteRestClient, GearService gearService) {
        this.athleteRestClient = athleteRestClient;
        this.gearService = gearService;
    }

    @Override
    public void sync(Athlete athlete) {
        Athlete freshAthlete = athleteRestClient.getAthlete(athlete.getAuthToken());
        freshAthlete.getGears()
                .stream()
                .peek(gear -> gear.setAthleteId(athlete.getId()))
                .forEach(gearService::update);
    }
}
