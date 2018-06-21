package com.tpo.fitme.domain.summary;

import lombok.Getter;

import java.util.List;

/**
 * @author Tiberiu
 * @since 05.06.18
 */
@Getter
public class Summary {

    private final List<ActivitiesSummary> activitiesSummaries;
    private final float totalKm;
    private final float totalCalories;

    public Summary(List<ActivitiesSummary> activitiesSummaries) {
        this.activitiesSummaries = activitiesSummaries;
        totalKm = sumKm();
        totalCalories = sumCalories();
    }

    private float sumKm() {
        return activitiesSummaries.stream()
                .map(ActivitiesSummary::getDistance)
                .reduce(Float::sum)
                .orElse((float) 0);
    }

    private float sumCalories() {
        return activitiesSummaries.stream()
                .map(ActivitiesSummary::getCalories)
                .reduce(Long::sum)
                .orElse(0L);
    }
}
