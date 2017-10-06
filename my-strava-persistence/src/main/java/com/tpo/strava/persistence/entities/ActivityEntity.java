package com.tpo.strava.persistence.entities;

import javax.persistence.*;

/**
 * Created by Tiberiu on 23/10/15.
 */
@Entity(name = "activities")
public class ActivityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String activityId;

    @Column
    private Float calories;

    @Column
    private Float distance;

    @Column
    private long insertDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getCalories() {
        return calories;
    }

    public void setCalories(Float calories) {
        this.calories = calories;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public long getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(long insertDate) {
        this.insertDate = insertDate;
    }

    public Float getDistance() {
        return distance;
    }

    public void setDistance(Float distance) {
        this.distance = distance;
    }
}
