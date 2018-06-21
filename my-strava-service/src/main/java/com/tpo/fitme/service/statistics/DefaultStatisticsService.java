package com.tpo.fitme.service.statistics;

import com.tpo.fitme.domain.Sport;
import com.tpo.fitme.domain.activity.Activity;
import com.tpo.fitness.service.activity.ActivitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author Tiberiu
 * @since 19.06.18
 */
@Service
public class DefaultStatisticsService implements StatisticsService {

    private final ActivitiesService activitiesService;

    @Autowired
    public DefaultStatisticsService(ActivitiesService activitiesService) {
        this.activitiesService = activitiesService;
    }

    @Override
    public float getTotalDistance(Sport sport) {
        return activitiesService.findAllBySport(sport)
                .stream()
                .map(Activity::getDistance)
                .reduce(0f, Float::sum)
                .longValue();
    }

    @Override
    public float getTotalDistance(Sport sport, LocalDateTime since) {
        return activitiesService.findAllBySportSince(sport, since)
                .stream()
                .map(Activity::getDistance)
                .reduce(0f, Float::sum)
                .longValue();
    }
}
