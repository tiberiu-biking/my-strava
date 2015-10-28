package com.tpo.strava.persistence.entities;

import com.google.common.base.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by Tiberiu on 23/10/15.
 */
@Entity
public class Activity {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private Float calories;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Activity activity = (Activity) o;
        return Objects.equal(id, activity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
