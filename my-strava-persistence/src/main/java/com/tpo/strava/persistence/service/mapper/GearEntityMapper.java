package com.tpo.strava.persistence.service.mapper;

import com.tpo.fitme.domain.Gear;
import com.tpo.strava.persistence.entities.GearEntity;
import org.springframework.stereotype.Service;

/**
 * @author Tiberiu Popa
 * @since 6/2/2018
 */
@Service
public class GearEntityMapper implements Mapper<GearEntity, Gear> {

    @Override
    public GearEntity from(Gear from) {
        GearEntity gearEntity = new GearEntity();
        gearEntity.setExternalId(from.getExternalId());
        gearEntity.setId(from.getId());
        gearEntity.setBrandName(from.getBrandName());
        gearEntity.setDescription(from.getDescription());
        gearEntity.setDistance(from.getDistance());
        gearEntity.setFrameType(from.getFrameType());
        gearEntity.setModelName(from.getModelName());
        gearEntity.setName(from.getName());
        return gearEntity;
    }

    @Override
    public Gear to(GearEntity to) {
        Gear gear = new Gear();
        gear.setId(to.getId());
        gear.setExternalId(to.getExternalId());
        gear.setBrandName(to.getBrandName());
        gear.setDescription(to.getDescription());
        gear.setDistance(to.getDistance());
        gear.setFrameType(to.getFrameType());
        gear.setModelName(to.getModelName());
        gear.setName(to.getName());
        return gear;
    }
}
