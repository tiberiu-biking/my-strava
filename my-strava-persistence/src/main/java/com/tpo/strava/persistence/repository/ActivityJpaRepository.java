package com.tpo.strava.persistence.repository;

import com.tpo.strava.persistence.entities.ActivityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

/**
 * Created by Tiberiu on 23/10/15.
 */
public interface ActivityJpaRepository extends JpaRepository<ActivityEntity, Long> {
    ActivityEntity findFirstByAthleteIdOrderByStartDateDesc(String athleteId);

    List<ActivityEntity> findAllByOrderByStartDateDesc();

    List<ActivityEntity> findAllByStartDateAfter(Date after);
}
