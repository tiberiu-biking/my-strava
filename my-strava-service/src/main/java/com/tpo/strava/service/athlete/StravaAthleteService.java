package com.tpo.strava.service.athlete;

import com.tpo.strava.service.client.StravaRestClient;
import com.tpo.strava.service.domain.Athlete;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Tiberiu on 25/10/15.
 */
@SpringComponent
@UIScope
public class StravaAthleteService implements AthleteService {

    private static final Logger logger = LoggerFactory.getLogger(StravaAthleteService.class);

    @Override
    public Athlete getAthlete(String authToken) {
        logger.info("Getting athlete using authorization code: " + authToken);
        StravaRestClient stravaRestClient = new StravaRestClient(authToken);
        return stravaRestClient.getAthlete();
    }
}
