package com.tpo.strava.persistence.repository;

import com.tpo.strava.persistence.entities.AthleteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by Tiberiu on 23/10/15.
 */
public interface AthleteRepository extends JpaRepository<AthleteEntity, Long> {
    Optional<AthleteEntity> findByExternalId(String id);
}
