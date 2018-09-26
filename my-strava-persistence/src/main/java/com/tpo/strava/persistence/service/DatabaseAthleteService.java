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
        athleteRepository.saveAndFlush(athleteEntityMapper.from(athlete));
    }

    @Override
    public Athlete findByExternalId(String id) {
        return athleteRepository.findByExternalId(id)
                .map(athleteEntityMapper::to)
                .orElse(null);
    }

    @Override
    public Athlete fineOne(Long id) {
        return athleteEntityMapper.to(athleteRepository.findOne(id));
    }

    @Override
    public void updateAuthToken(Athlete athlete) {
        athleteRepository.findByExternalId(athlete.getExternalId())
                .ifPresent(athleteEntity -> {
                    athleteEntity.setAuthToken(athlete.getAuthToken());
                    athleteRepository.saveAndFlush(athleteEntity);
                });
    }
}
