package com.tpo.fitme.service.statistics;

import com.tpo.fitness.domain.summary.Summary;

import java.time.Duration;

/**
 * @author Tiberiu
 * @since 05.06.18
 */
public interface ActivitiesSummaryService {

    Summary generateSummary();

    Summary generateSummarySince(Duration duration);

    float getTripsAroundTheWorld();

    float getTripsToTheMoon();

    long getBeersBurned();

    long getBurgerBurned();
}
