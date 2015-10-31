package com.tpo.strava.service.domain;

/**
 * Created by Tiberiu on 28/10/15.
 */
public class ActivitiesSummary {

    private Long calories;
    private Float distance;
    private Integer month;
    private Integer year;

    public ActivitiesSummary() {
        distance = 0F;
        calories = 0L;
    }

    public String getPeriod() {
        return month + "/" + year;
    }

    public Long getCalories() {
        return calories;
    }

    public void setCalories(Long calories) {
        this.calories = calories;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActivitiesSummary that = (ActivitiesSummary) o;
        return com.google.common.base.Objects.equal(month, that.month) &&
                com.google.common.base.Objects.equal(year, that.year);
    }

    @Override
    public int hashCode() {
        return com.google.common.base.Objects.hashCode(month, year);
    }

    public Float getDistance() {
        return distance;
    }

    public void setDistance(Float distance) {
        this.distance = distance;
    }
}
