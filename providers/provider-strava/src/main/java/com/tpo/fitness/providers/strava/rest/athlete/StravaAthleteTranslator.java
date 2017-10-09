package com.tpo.fitness.providers.strava.rest.athlete;

import com.tpo.fitness.domain.Athlete;

/**
 * @author Tiberiu
 * @since 08.10.17
 */
public class StravaAthleteTranslator {

    public static Athlete translate(StravaAthlete stravaAthlete) {
        Athlete athlete = new Athlete();
        athlete.setFirstName(stravaAthlete.getFirstname());
        athlete.setLastName(stravaAthlete.getLastname());
        athlete.setProfileMediumPicture(stravaAthlete.getProfile_medium());
        return athlete;
    }
}
