package com.tpo.strava.persistence.service.repository.repository;

import com.tpo.fitness.domain.Athlete;
import com.tpo.strava.persistence.entities.AthleteEntity;
import com.tpo.strava.persistence.repository.AthleteJpaRepository;
import com.tpo.strava.persistence.service.mapper.Translator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Tiberiu Popa
 * @since 6/2/2018
 */
@Service
public class AthleteDatabaseRepository implements AthleteRepository {

    private final AthleteJpaRepository               athleteJpaRepository;
    private final Translator<AthleteEntity, Athlete> athleteEntityTranslator;

    @Autowired
    public AthleteDatabaseRepository(AthleteJpaRepository athleteJpaRepository, Translator<AthleteEntity, Athlete> athleteEntityTranslator) {
        this.athleteJpaRepository = athleteJpaRepository;
        this.athleteEntityTranslator = athleteEntityTranslator;
    }

    @Override
    public void save(Athlete athlete) {
        athleteJpaRepository.save(athleteEntityTranslator.from(athlete));
    }

    @Override
    public boolean exists(String id) {
        return athleteJpaRepository.exists(id);
    }

    @Override
    public Athlete fineOne(String id) {
        return athleteEntityTranslator.to(athleteJpaRepository.findOne(id));
    }

    @Override
    public void updateAuthToken(Athlete athlete) {
        athleteJpaRepository.save(athleteEntityTranslator.from(athlete));
    }
}
