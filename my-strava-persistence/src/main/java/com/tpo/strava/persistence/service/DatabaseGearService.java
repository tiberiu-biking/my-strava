package com.tpo.strava.persistence.service;

import com.tpo.fitme.domain.Athlete;
import com.tpo.fitme.domain.Gear;
import com.tpo.strava.persistence.entities.GearEntity;
import com.tpo.strava.persistence.repository.GearRepository;
import com.tpo.strava.persistence.service.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Tiberiu
 * @since 06.10.17
 */
@Service
class DatabaseGearService implements GearService {

    private final GearRepository gearRepository;
    private final Mapper<GearEntity, Gear> gearEntityMapper;

    @Autowired
    public DatabaseGearService(GearRepository gearRepository, Mapper<GearEntity, Gear> gearEntityMapper) {
        this.gearRepository = gearRepository;
        this.gearEntityMapper = gearEntityMapper;
    }


    @Override
    public Gear save(Gear gear) {
        GearEntity gearEntity = gearEntityMapper.from(gear);
        return gearEntityMapper.to(gearRepository.saveAndFlush(gearEntity));
    }

    @Override
    public List<Gear> findAllByAthlete(Athlete athlete) {
        return gearRepository.findAllByAthleteId(athlete.getId())
                .parallelStream()
                .map(gearEntityMapper::to)
                .collect(Collectors.toList());
    }

    @Override
    public Gear update(Gear gear) {
        GearEntity existingEntity = gearRepository.findByExternalId(gear.getExternalId());
        GearEntity gearEntity = gearEntityMapper.from(gear);
        if (existingEntity != null) {
            gearEntity.setId(existingEntity.getId());
        }
        return gearEntityMapper.to(gearRepository.saveAndFlush(gearEntity));
    }
}
