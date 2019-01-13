package com.tpo.fitme.domain;

import lombok.Getter;

/**
 * @author Tiberiu
 * @since 19.06.18
 */
@Getter
public enum Sport {

    SOCCER("Soccer", 10),
    HIKE("Hiking"),
    WORKOUT("Workout"),
    ALPINESKI("Alpine Ski", 7),
    RIDE("Cycling"),
    ROAD("Road Cycling"),
    RUN("Running", 15),
    SWIM("Swimming"),
    WALK("Walking"),
    CROSSFIT("Crossfit"),
    YOGA("Yoga"),
    TRX("TRX"),
    HIIT("HIIT"),
    MTB("Mountain Biking");

    private final String name;
    private final int caloriesPerMinute;

    Sport(String name, int caloriesPerMinute) {
        this.name = name;
        this.caloriesPerMinute = caloriesPerMinute;
    }

    Sport(String name) {
        this.name = name;
        this.caloriesPerMinute = 0;
    }
}
