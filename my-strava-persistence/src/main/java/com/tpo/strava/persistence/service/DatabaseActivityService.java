package com.tpo.strava.persistence.service;

import com.tpo.fitme.domain.Athlete;
import com.tpo.fitme.domain.Sport;
import com.tpo.fitme.domain.activity.Activity;
import com.tpo.strava.persistence.entities.ActivityEntity;
import com.tpo.strava.persistence.repository.ActivityRepository;
import com.tpo.strava.persistence.service.mapper.Mapper;
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
class DatabaseActivityService implements ActivityService {

    private static final LocalDateTime BEGINNING_OF_TIME = LocalDateTime.ofInstant(Instant.EPOCH, ZoneId.systemDefault());
    private final ActivityRepository activityRepository;
    private final Mapper<ActivityEntity, Activity> activityEntityMapper;

    @Autowired
    public DatabaseActivityService(ActivityRepository activityRepository,
                                   Mapper<ActivityEntity, Activity> activityEntityMapper) {
        this.activityRepository = activityRepository;
        this.activityEntityMapper = activityEntityMapper;
    }

    @Override
    @Transactional
    public Activity save(Activity activity) {
        ActivityEntity activityEntity = activityEntityMapper.from(activity);
        activityEntity.setInsertDate(Instant.now().getEpochSecond());
        return activityEntityMapper.to(activityRepository.saveAndFlush(activityEntity));
    }

    @Override
    public List<Activity> findAll() {
        List<ActivityEntity> activityEntities = activityRepository.findAll();
        return activityEntities.parallelStream()
                .map(activityEntityMapper::to)
                .collect(Collectors.toList());
    }

    @Override
    public List<Activity> findAllBySport(Sport sport) {
        List<ActivityEntity> activityEntities = activityRepository.findBySport(sport);
        return activityEntities.parallelStream()
                .map(activityEntityMapper::to)
                .collect(Collectors.toList());
    }

    @Override
    public List<Activity> findAllBySportAndYear(Sport sport, int year) {
        LocalDateTime startOfTheYear = LocalDateTime.of(year, 1, 1, 0, 0, 0);
        LocalDateTime endOfTheYear = LocalDateTime.of(year, 12, 31, 23, 59, 59);
        List<ActivityEntity> activityEntities = activityRepository.findBySportAndStartDateBetween(sport, startOfTheYear, endOfTheYear);
        return activityEntities.parallelStream()
                .map(activityEntityMapper::to)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Activity> findByExternalId(String id) {
        ActivityEntity activityEntity = activityRepository.findByExternalId(id);
        if (activityEntity != null) {
            return Optional.of(activityEntityMapper.to(activityEntity));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public List<Activity> findAllForTheLast(Duration duration) {
        LocalDateTime after = LocalDateTime.now().minusNanos(duration.toNanos());
        List<ActivityEntity> activityEntities = activityRepository.findAllByStartDateAfter(after);
        return activityEntities.parallelStream()
                .map(activityEntityMapper::to)
                .collect(Collectors.toList());
    }

    @Override
    public List<Activity> findAllInChronologicalOrder() {
        return activityRepository.findAllByOrderByStartDateDesc()
                .stream()
                .map(activityEntityMapper::to)
                .collect(Collectors.toList());
    }

    @Override
    public Activity findFirstByOrderByInsertDateDesc(Athlete athlete) {
        return activityEntityMapper.to(activityRepository.findFirstByAthleteIdOrderByStartDateDesc(athlete.getId()));
    }

    @Override
    public LocalDateTime getLastStartDateByAthlete(Athlete athlete) {
        ActivityEntity lastActivity = activityRepository.findFirstByAthleteIdOrderByStartDateDesc(athlete.getId());
        if (lastActivity != null) {
            return lastActivity.getStartDate();
        } else {
            return BEGINNING_OF_TIME;
        }
    }
}
