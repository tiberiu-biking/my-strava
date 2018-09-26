package com.tpo.fitme.strava.client.rest.athlete;

import com.tpo.fitme.domain.Athlete;
import com.tpo.fitme.domain.Gear;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Tiberiu
 * @since 08.10.17
 */
@Component
class StravaAthleteMapper {

    private final StravaGearMapper stravaGearMapper;

    @Autowired
    StravaAthleteMapper(StravaGearMapper stravaGearMapper) {
        this.stravaGearMapper = stravaGearMapper;
    }

    Athlete map(StravaAthlete stravaAthlete) {
        Athlete athlete = new Athlete();
        athlete.setExternalId(stravaAthlete.getId());
        athlete.setFirstName(stravaAthlete.getFirstName());
        athlete.setLastName(stravaAthlete.getLastName());
        athlete.setProfileMediumPicture(stravaAthlete.getProfileMedium());
        athlete.getGears().addAll(mapToGear(stravaAthlete.getBikes()));
        athlete.getGears().addAll(mapToGear(stravaAthlete.getShoes()));
        return athlete;
    }

    private List<Gear> mapToGear(List<StravaGear> stravaGears) {
        return stravaGears
                .stream()
                .map(stravaGearMapper::map)
                .collect(Collectors.toList());
    }
}
