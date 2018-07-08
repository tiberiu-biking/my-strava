package com.tpo.strava.persistence.service.mapper;

import com.tpo.fitme.domain.Athlete;
import com.tpo.strava.persistence.entities.AthleteEntity;
import org.springframework.stereotype.Service;

/**
 * @author Tiberiu Popa
 * @since 6/2/2018
 */
@Service
public class AthleteEntityMapper implements Mapper<AthleteEntity, Athlete> {

    @Override
    public AthleteEntity from(Athlete from) {
        AthleteEntity athleteEntity = new AthleteEntity();
        athleteEntity.setId(from.getId());
        athleteEntity.setAuthToken(from.getAuthToken());
        return athleteEntity;
    }

    @Override
    public Athlete to(AthleteEntity to) {
        Athlete athlete  = new Athlete();
        athlete.setId(to.getId());
        athlete.setAuthToken(to.getAuthToken());
        return athlete;
    }
}
