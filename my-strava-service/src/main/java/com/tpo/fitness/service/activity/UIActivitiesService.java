package com.tpo.fitness.service.activity;

import com.tpo.fitness.domain.ActivitiesSummary;
import com.tpo.fitness.domain.activity.Activity;
import com.tpo.strava.persistence.service.repository.repository.ActivityDatabaseRepository;
import com.tpo.strava.persistence.service.repository.repository.ActivityRepository;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Tiberiu on 25/10/15.
 */
@Slf4j
@Service
public class UIActivitiesService implements ActivitiesService {

    private ActivityRepository activityRepository;

    @Autowired
    public UIActivitiesService(ActivityDatabaseRepository activityDatabaseRepository) {
        this.activityRepository = activityDatabaseRepository;
    }

    @Override
    public List<Activity> getAll() {
        return activityRepository.getAll();
    }

    @Override
    public List<Activity> getAllInChronologicalOrder() {
        return activityRepository.getAllInChronologicalOrder();
    }

    @Override
    public List<ActivitiesSummary> getSummary() {
        List<Activity> activities = activityRepository.getAll();
        if (!activities.isEmpty()) {
            DateTime firstStartDate = new DateTime(activities.get(0).getStartDate());

            List<ActivitiesSummary> activitiesSummaries = generateEmptySummaries(firstStartDate.year().get());

            for (Activity activity : activities) {
                DateTime startDate = new DateTime(activity.getStartDate());

                for (ActivitiesSummary summary : activitiesSummaries) {
                    if ((summary.getMonth() == startDate.monthOfYear().get()) &&
                            (summary.getYear() == startDate.year().get())) {
                        summary.setCalories(summary.getCalories() + activity.getCalories().longValue());
                        summary.setDistance(summary.getDistance() + (activity.getDistance() / 1000));
                    }
                }
            }
            return activitiesSummaries;
        } else {
            return Collections.emptyList();
        }
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
