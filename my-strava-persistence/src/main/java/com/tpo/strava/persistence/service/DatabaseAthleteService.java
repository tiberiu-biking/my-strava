package com.tpo.strava.persistence.service;

import com.tpo.fitme.domain.Athlete;
import com.tpo.strava.persistence.entities.AthleteEntity;
import com.tpo.strava.persistence.repository.AthleteRepository;
import com.tpo.strava.persistence.service.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Tiberiu Popa
 * @since 6/2/2018
 */
@Service
public class DatabaseAthleteService implements AthleteService {

    private final AthleteRepository athleteRepository;
    private final Mapper<AthleteEntity, Athlete> athleteEntityMapper;

    @Autowired
    public DatabaseAthleteService(AthleteRepository athleteRepository, Mapper<AthleteEntity, Athlete> athleteEntityMapper) {
        this.athleteRepository = athleteRepository;
        this.athleteEntityMapper = athleteEntityMapper;
    }

    @Override
    public void save(Athlete athlete) {
        athleteRepository.save(athleteEntityMapper.from(athlete));
    }

    @Override
    public boolean exists(String id) {
        return athleteRepository.exists(id);
    }

    @Override
    public Athlete fineOne(String id) {
        return athleteEntityMapper.to(athleteRepository.findOne(id));
    }

    @Override
    public void updateAuthToken(Athlete athlete) {
        athleteRepository.save(athleteEntityMapper.from(athlete));
    }
}
