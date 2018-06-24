package com.tpo.strava.persistence.repository;

import com.tpo.fitme.domain.Sport;
import com.tpo.strava.persistence.entities.ActivityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Tiberiu on 23/10/15.
 */
public interface ActivityJpaRepository extends JpaRepository<ActivityEntity, Long> {
    ActivityEntity findFirstByAthleteIdOrderByStartDateDesc(String athleteId);

    List<ActivityEntity> findAllByOrderByStartDateDesc();

    List<ActivityEntity> findAllByStartDateAfter(LocalDateTime after);

    ActivityEntity findByExternalId(String externalId);

    List<ActivityEntity> findBySport(Sport sport);

    List<ActivityEntity> findBySportAndStartDateBetween(Sport sport, LocalDateTime from, LocalDateTime to);
}
