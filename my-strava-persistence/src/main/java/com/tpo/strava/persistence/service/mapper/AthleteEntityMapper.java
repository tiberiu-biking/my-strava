package com.tpo.strava.persistence.service.mapper;

import com.tpo.fitme.domain.Athlete;
import com.tpo.fitme.domain.Gear;
import com.tpo.strava.persistence.entities.AthleteEntity;
import com.tpo.strava.persistence.entities.GearEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Tiberiu Popa
 * @since 6/2/2018
 */
@Service
public class AthleteEntityMapper implements Mapper<AthleteEntity, Athlete> {

    private final Mapper<GearEntity, Gear> gearEntityMapper;

    @Autowired
    public AthleteEntityMapper(Mapper<GearEntity, Gear> gearEntityMapper) {
        this.gearEntityMapper = gearEntityMapper;
    }

    @Override
    public AthleteEntity from(Athlete from) {
        AthleteEntity athleteEntity = new AthleteEntity();
        athleteEntity.setExternalId(from.getExternalId());
        athleteEntity.setAuthToken(from.getAuthToken());
        athleteEntity.getGears().addAll(mapGears(from.getGears()));
        return athleteEntity;
    }

    @Override
    public Athlete to(AthleteEntity to) {
        Athlete athlete = new Athlete();
        athlete.setId(to.getId());
        athlete.setExternalId(to.getExternalId());
        athlete.setAuthToken(to.getAuthToken());
        athlete.getGears().addAll(mapGearsEntities(to.getGears()));
        return athlete;
    }

    private List<GearEntity> mapGears(List<Gear> gears) {
        return gears
                .stream()
                .map(gearEntityMapper::from)
                .collect(Collectors.toList());
    }

    private List<Gear> mapGearsEntities(List<GearEntity> gearEntityList) {
        return gearEntityList
                .stream()
                .map(gearEntityMapper::to)
                .collect(Collectors.toList());
    }

}
