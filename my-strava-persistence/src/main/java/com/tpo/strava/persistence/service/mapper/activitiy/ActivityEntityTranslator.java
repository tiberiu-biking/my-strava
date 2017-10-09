package com.tpo.strava.persistence.service.mapper.activitiy;

import com.tpo.fitness.domain.activity.Activity;
import com.tpo.strava.persistence.entities.ActivityEntity;
import com.tpo.strava.persistence.service.mapper.Translator;
import org.springframework.stereotype.Service;

/**
 * @author Tiberiu
 * @since 06.10.17
 */
@Service
public class ActivityEntityTranslator implements Translator<ActivityEntity, Activity> {

    @Override
    public ActivityEntity from(Activity activity) {
        ActivityEntity activityEntity = new ActivityEntity();
        activityEntity.setActivityId(activity.getId());
        activityEntity.setDistance(activity.getDistance());
        activityEntity.setCalories(activity.getCalories());
        activityEntity.setStartDate(activity.getStartDate());
        return activityEntity;
    }

    @Override
    public Activity to(ActivityEntity activityEntity) {
        Activity activity = new Activity();
        activity.setId(activityEntity.getActivityId());
        activity.setDistance(activityEntity.getDistance());
        activity.setCalories(activityEntity.getCalories());
        activity.setStartDate(activityEntity.getStartDate());
        return activity;
    }
}
