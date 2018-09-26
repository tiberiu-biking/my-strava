package com.tpo.strava.persistence.repository;

import com.tpo.strava.persistence.entities.GearEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Tiberiu on 23/10/15.
 */
public interface GearRepository extends JpaRepository<GearEntity, Long> {
    List<GearEntity> findAllByAthleteId(Long id);

    GearEntity findByExternalId(String externalId);
}
