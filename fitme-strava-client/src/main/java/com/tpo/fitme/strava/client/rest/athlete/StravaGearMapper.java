package com.tpo.fitme.strava.client.rest.athlete;

import com.tpo.fitme.domain.Gear;
import org.springframework.stereotype.Component;

/**
 * @author Tiberiu
 * @since 08.10.17
 */
@Component
class StravaGearMapper {

    Gear map(StravaGear stravaGear) {
        Gear gear = new Gear();
        gear.setExternalId(stravaGear.getId());
        gear.setBrandName(stravaGear.getBrandName());
        gear.setDescription(stravaGear.getDescription());
        gear.setDistance(stravaGear.getDistance() / 1000);
        gear.setFrameType(stravaGear.getFrameType());
        gear.setModelName(stravaGear.getModelName());
        gear.setName(stravaGear.getName());
        return gear;
    }
}
