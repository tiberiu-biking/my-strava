package com.tpo.fitness.service.activity;

import com.tpo.fitness.domain.ActivitiesSummary;
import com.tpo.fitness.domain.activity.Activity;
import com.tpo.strava.persistence.service.repository.repository.ActivityDatabaseRepository;
import com.tpo.strava.persistence.service.repository.repository.ActivityRepository;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tiberiu on 25/10/15.
 */
//@CacheConfig(cacheNames = "activities")
@SpringComponent
@UIScope
@Service
public class UIActivitiesService implements ActivitiesService {

    private static final Logger logger = LoggerFactory.getLogger(UIActivitiesService.class);

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
    public List<ActivitiesSummary> getSummary() {
        List<Activity> activities = activityRepository.getAll();
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
