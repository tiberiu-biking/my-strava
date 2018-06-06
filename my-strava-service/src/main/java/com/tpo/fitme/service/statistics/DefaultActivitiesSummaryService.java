package com.tpo.fitme.service.statistics;

import com.tpo.fitness.domain.activity.Activity;
import com.tpo.fitness.domain.summary.ActivitiesSummary;
import com.tpo.fitness.domain.summary.Summary;
import com.tpo.fitness.service.activity.ActivitiesService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.tpo.fitme.service.constants.Constants.*;

/**
 * @author Tiberiu
 * @since 05.06.18
 */
@Service
public class DefaultActivitiesSummaryService implements ActivitiesSummaryService {

    private final ActivitiesService activityRepository;

    @Autowired
    public DefaultActivitiesSummaryService(ActivitiesService activityRepository) {
        this.activityRepository = activityRepository;
    }

    @Override
    public Summary generateSummary() {
        return generateSummarySince(Duration.ZERO);
    }

    @Override
    public Summary generateSummarySince(Duration duration) {
        List<ActivitiesSummary> activitiesSummaries = Collections.emptyList();
        List<Activity> activities;
        if (duration.equals(Duration.ZERO)) {
            activities = activityRepository.findAll();
        } else {
            activities = activityRepository.findAllSinceTheLast(duration);
        }
        if (!activities.isEmpty()) {
            DateTime firstStartDate = new DateTime(activities.get(0).getStartDate());
            activitiesSummaries = generateEmptySummaries(firstStartDate.year().get());

            for (Activity activity : activities) {
                DateTime startDate = new DateTime(activity.getStartDate());

                for (ActivitiesSummary summary : activitiesSummaries) {
                    if ((summary.getMonth() == startDate.monthOfYear().get()) &&
                            (summary.getYear() == startDate.year().get())) {
                        summary.setCalories(summary.getCalories() + activity.getCalories());
                        summary.setDistance(summary.getDistance() + activity.getDistance());
                    }
                }
            }
        }
        return new Summary(activitiesSummaries);
    }

    @Override
    public float getTripsAroundTheWorld() {
        return divide(generateSummary().getTotalKm(), ECUATOR_LENGTH_KM);
    }

    @Override
    public float getTripsToTheMoon() {
        return divide(generateSummary().getTotalKm(), DISTANCE_TO_THE_MOON_KM);
    }

    @Override
    public long getBeersBurned() {
        return Math.round(divide(generateSummary().getTotalCalories(), CALORIES_PER_BEER));
    }

    @Override
    public long getBurgerBurned() {
        return Math.round(divide(generateSummary().getTotalCalories(), CALORIES_PER_BURGER));
    }

    public float divide(float number, float by) {
        float i = number / by;
        return (float) Math.round(i * 100000) / 100000;
    }

    private List<ActivitiesSummary> generateEmptySummaries(int startYear) {
        ArrayList<ActivitiesSummary> resultList = new ArrayList<>();

        int currentYear = DateTime.now().year().get();
        int currentMonth = DateTime.now().monthOfYear().get();

        for (int year = startYear; year <= currentYear; year++)
            for (int month = 1; month <= currentMonth; month++) {
                ActivitiesSummary newSummary = new ActivitiesSummary();
                newSummary.setMonth(month);
                newSummary.setYear(year);
                newSummary.setDateTime(new DateTime(year, month, 1, 0, 0, 0));
                resultList.add(newSummary);
            }
        return resultList;
    }

}
