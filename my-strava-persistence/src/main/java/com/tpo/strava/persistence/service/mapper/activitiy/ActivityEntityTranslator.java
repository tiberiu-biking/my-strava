package com.tpo.strava.persistence.service.mapper.activitiy;

import com.tpo.strava.persistence.entities.ActivityEntity;
import com.tpo.strava.persistence.service.mapper.Translator;
import com.tpo.strava.service.domain.activity.Activity;
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
        return activityEntity;
    }

    @Override
    public Activity to(ActivityEntity activityEntity) {
        Activity activity = new Activity();
        activity.setId(activityEntity.getActivityId());
        activity.setDistance(activityEntity.getDistance());
        activity.setCalories(activityEntity.getCalories());
        return activity;
    }
}
