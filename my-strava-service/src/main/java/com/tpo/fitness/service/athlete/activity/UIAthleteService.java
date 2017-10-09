package com.tpo.fitness.service.athlete.activity;

import com.tpo.fitness.domain.Athlete;
import com.tpo.fitness.providers.api.service.AthleteRestClient;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Tiberiu on 25/10/15.
 */
@SpringComponent
@UIScope
@Service
public class UIAthleteService implements AthleteService {

    private static final Logger logger = LoggerFactory.getLogger(UIAthleteService.class);

    private final AthleteRestClient stravaAthleteRestClient;

    @Autowired
    public UIAthleteService(AthleteRestClient stravaAthleteRestClient) {
        this.stravaAthleteRestClient = stravaAthleteRestClient;
    }

    @Override
    public Athlete getAthlete() {
        return stravaAthleteRestClient.getAthlete();
    }
}
