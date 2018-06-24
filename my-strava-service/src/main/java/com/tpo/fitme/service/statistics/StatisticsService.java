package com.tpo.fitme.service.statistics;

import com.tpo.fitme.domain.Sport;

/**
 * @author Tiberiu
 * @since 19.06.18
 */
public interface StatisticsService {

    float getTotalDistance(Sport sport);

    float getTotalDistance(Sport sport, int year);
}
