package com.tpo.strava.persistence.service.repository.repository;

import com.tpo.fitme.domain.Athlete;
import com.tpo.fitme.domain.Sport;
import com.tpo.fitme.domain.activity.Activity;
import com.tpo.strava.persistence.entities.ActivityEntity;
import com.tpo.strava.persistence.repository.ActivityJpaRepository;
import com.tpo.strava.persistence.service.mapper.Translator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Tiberiu
 * @since 06.10.17
 */
@Service
class ActivityDatabaseRepository implements ActivityRepository {

    private static final LocalDateTime BEGINNING_OF_TIME = LocalDateTime.ofInstant(Instant.EPOCH, ZoneId.systemDefault());
    private final ActivityJpaRepository activityJpaRepository;
    private final Translator<ActivityEntity, Activity> activityEntityTranslator;

    @Autowired
    public ActivityDatabaseRepository(ActivityJpaRepository activityJpaRepository,
                                      Translator<ActivityEntity, Activity> activityEntityTranslator) {
        this.activityJpaRepository = activityJpaRepository;
        this.activityEntityTranslator = activityEntityTranslator;
    }

    @Override
    @Transactional
    public Activity save(Activity activity) {
        ActivityEntity activityEntity = activityEntityTranslator.from(activity);
        activityEntity.setInsertDate(Instant.now().getEpochSecond());
        return activityEntityTranslator.to(activityJpaRepository.saveAndFlush(activityEntity));
    }

    @Override
    public List<Activity> findAll() {
        List<ActivityEntity> activityEntities = activityJpaRepository.findAll();
        return activityEntities.parallelStream()
                .map(activityEntityTranslator::to)
                .collect(Collectors.toList());
    }

    @Override
    public List<Activity> findBySport(Sport sport) {
        List<ActivityEntity> activityEntities = activityJpaRepository.findBySport(sport);
        return activityEntities.parallelStream()
                .map(activityEntityTranslator::to)
                .collect(Collectors.toList());
    }

    @Override
    public List<Activity> findBySportAndStartDateAfter(Sport sport, LocalDateTime date) {
        List<ActivityEntity> activityEntities = activityJpaRepository.findBySportAndStartDateAfter(sport, date);
        return activityEntities.parallelStream()
                .map(activityEntityTranslator::to)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Activity> findByExternalId(String id) {
        ActivityEntity activityEntity = activityJpaRepository.findByExternalId(id);
        if (activityEntity != null) {
            return Optional.of(activityEntityTranslator.to(activityEntity));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public List<Activity> findAllForTheLast(Duration duration) {
        LocalDateTime after = LocalDateTime.now().minusNanos(duration.toNanos());
        List<ActivityEntity> activityEntities = activityJpaRepository.findAllByStartDateAfter(after);
        return activityEntities.parallelStream()
                .map(activityEntityTranslator::to)
                .collect(Collectors.toList());
    }

    @Override
    public List<Activity> findAllInChronologicalOrder() {
        return activityJpaRepository.findAllByOrderByStartDateDesc()
                .stream()
                .map(activityEntityTranslator::to)
                .collect(Collectors.toList());
    }

    @Override
    public Activity findFirstByOrderByInsertDateDesc(Athlete athlete) {
        return activityEntityTranslator.to(activityJpaRepository.findFirstByAthleteIdOrderByStartDateDesc(athlete.getId()));
    }

    @Override
    public LocalDateTime getLastStartDateByAthlete(Athlete athlete) {
        ActivityEntity lastActivity = activityJpaRepository.findFirstByAthleteIdOrderByStartDateDesc(athlete.getId());
        if (lastActivity != null) {
            return lastActivity.getStartDate();
        } else {
            return BEGINNING_OF_TIME;
        }
    }
}
