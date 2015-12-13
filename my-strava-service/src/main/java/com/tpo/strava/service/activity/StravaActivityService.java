package com.tpo.strava.service.activity;

import com.tpo.strava.service.client.StravaRestClient;
import com.tpo.strava.service.domain.ActivitiesSummary;
import com.tpo.strava.service.domain.activity.Activity;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tiberiu on 25/10/15.
 */
@SpringComponent
@UIScope
public class StravaActivityService implements ActivityService {

    private static final Logger logger = LoggerFactory.getLogger(StravaActivityService.class);

    @Override
    public List<Activity> getActivities(String authToken) {
        logger.info("Getting activities using authorization code: " + authToken);
        StravaRestClient stravaRestClient = new StravaRestClient(authToken);
        List<Activity> summaryActivityList = stravaRestClient.getActivities();

        List<Activity> activities = new ArrayList<>();
        for (Activity summaryActivity : summaryActivityList) {
            Activity activity = stravaRestClient.getActivity(summaryActivity.getId());
            activities.add(activity);
        }

        return activities;
    }

    @Override
    public List<ActivitiesSummary> getActivitiesSummary(String authToken) {
        List<Activity> activities = getActivities(authToken);
        DateTime firstStartDate = new DateTime(activities.get(0).getStart_date());

        List<ActivitiesSummary> activitiesSummaries = generateEmptySummaries(firstStartDate.year().get());

        for (Activity activity : activities) {
            DateTime startDate = new DateTime(activity.getStart_date());

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
