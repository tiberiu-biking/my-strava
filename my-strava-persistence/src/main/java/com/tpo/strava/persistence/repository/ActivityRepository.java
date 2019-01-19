package com.tpo.strava.persistence.repository;

import com.tpo.fitme.domain.Sport;
import com.tpo.strava.persistence.entities.ActivityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Tiberiu on 23/10/15.
 */
public interface ActivityRepository extends JpaRepository<ActivityEntity, Long> {

    List<ActivityEntity> findAllByAthleteId(Long athleteId);

    ActivityEntity findFirstByAthleteIdOrderByStartDateDesc(Long athleteId);

    List<ActivityEntity> findAllByAthleteIdOrderByStartDateDesc(Long athleteId);

    List<ActivityEntity> findAllByAthleteIdAndStartDateAfter(Long athleteId, LocalDateTime after);

    ActivityEntity findByAthleteIdAndExternalId(Long athleteId, String externalId);

    List<ActivityEntity> findByAthleteIdAndSport(Long athleteId, Sport sport);

    List<ActivityEntity> findByAthleteIdAndSportAndStartDateBetween(Long athleteId, Sport sport, LocalDateTime from, LocalDateTime to);

    List<ActivityEntity> findByAthleteIdAndStartDateBetween(Long athleteId, LocalDateTime from, LocalDateTime to);
}
