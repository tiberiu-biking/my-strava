package com.tpo.strava.service.strava.gui.user;

import com.tpo.strava.service.athlete.AthleteService;
import com.tpo.strava.service.domain.Athlete;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;

/**
 * Created by Tiberiu on 02/11/15.
 */
public class UserPanel extends Panel {

    public UserPanel(AthleteService athleteService, String authToken) {
        Athlete athlete = athleteService.getAthlete(authToken);
        Label athleteNameLabel = new Label(athlete.getFirstname() + " " + athlete.getLastname());

        HorizontalLayout content = new HorizontalLayout();
        content.addComponent(athleteNameLabel);
        setContent(content);
    }
}
