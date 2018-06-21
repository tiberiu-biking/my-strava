package com.tpo.fitme.service.statistics;

import com.tpo.fitness.domain.Sport;

import java.time.LocalDateTime;

/**
 * @author Tiberiu
 * @since 19.06.18
 */
public interface StatisticsService {

    float getTotalDistance(Sport sport);

    float getTotalDistance(Sport sport, LocalDateTime since);
}
