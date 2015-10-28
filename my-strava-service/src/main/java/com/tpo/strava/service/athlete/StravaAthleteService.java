package com.tpo.strava.service.athlete;

import com.tpo.strava.service.domain.Athlete;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;

/**
 * Created by Tiberiu on 25/10/15.
 */
@SpringComponent
@UIScope
public class StravaAthleteService implements AthleteService {

    @Override
    public Athlete getAthlete() {
        Athlete athlete = new Athlete();
        athlete.setFirstname("Tiberiu");
        return athlete;
    }
}
