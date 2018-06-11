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
        activityEntity.setExternalId(activity.getExternalId());
        activityEntity.setAthleteId(activity.getAthleteId());
        activityEntity.setName(activity.getName());
        activityEntity.setDistance(activity.getDistance());
        activityEntity.setCalories(activity.getCalories());
        activityEntity.setStartDate(activity.getStartDate());
        activityEntity.setType(activity.getType());
        activityEntity.setElevation(activity.getElevation());
        activityEntity.setDuration(activity.getDuration());
        return activityEntity;
    }

    @Override
    public Activity to(ActivityEntity activityEntity) {
        Activity activity = new Activity();
        activity.setId(activityEntity.getId());
        activity.setExternalId(activityEntity.getExternalId());
        activity.setAthleteId(activityEntity.getAthleteId());
        activity.setDistance(activityEntity.getDistance());
        activity.setCalories(activityEntity.getCalories());
        activity.setStartDate(activityEntity.getStartDate());
        activity.setName(activityEntity.getName());
        activity.setType(activityEntity.getType());
        activity.setElevation(activityEntity.getElevation());
        activity.setDuration(activityEntity.getDuration());
        return activity;
    }
}
