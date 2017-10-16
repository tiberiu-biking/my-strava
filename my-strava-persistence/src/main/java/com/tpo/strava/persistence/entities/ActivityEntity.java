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
    private Long insertDate;

    @Column
    private String startDate;

    @Column
    private String name;

    @Column
    private String type;

    @Column
    private Float elevation;

    @Column
    private Integer duration;

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

    public void setInsertDate(Long insertDate) {
        this.insertDate = insertDate;
    }

    public Float getDistance() {
        return distance;
    }

    public void setDistance(Float distance) {
        this.distance = distance;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Float getElevation() {
        return elevation;
    }

    public void setElevation(Float elevation) {
        this.elevation = elevation;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }
}
