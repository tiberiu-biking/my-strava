package com.tpo.fitme.strava.client.rest.athlete;

import com.tpo.fitme.domain.Athlete;
import org.springframework.stereotype.Component;

/**
 * @author Tiberiu
 * @since 08.10.17
 */
@Component
class StravaAthleteMapper {

    Athlete map(StravaAthlete stravaAthlete) {
        Athlete athlete = new Athlete();
        athlete.setId(stravaAthlete.getId());
        athlete.setFirstName(stravaAthlete.getFirstName());
        athlete.setLastName(stravaAthlete.getLastName());
        athlete.setProfileMediumPicture(stravaAthlete.getProfileMedium());
        athlete.setShoes(stravaAthlete.getShoes());
        athlete.setBikes(stravaAthlete.getBikes());
        return athlete;
    }
}
