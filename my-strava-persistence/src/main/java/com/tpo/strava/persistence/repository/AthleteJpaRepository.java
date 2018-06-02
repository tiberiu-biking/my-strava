package com.tpo.strava.persistence.repository;

import com.tpo.strava.persistence.entities.AthleteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Tiberiu on 23/10/15.
 */
public interface AthleteJpaRepository extends JpaRepository<AthleteEntity, String> {
}
