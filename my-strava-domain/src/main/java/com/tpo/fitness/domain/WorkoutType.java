package com.tpo.fitness.domain;

import lombok.Getter;

/**
 * @author Tiberiu
 * @since 19.06.18
 */
@Getter
public enum WorkoutType {
    RACE(11),
    WORKOUT(12);

    private final int value;

    WorkoutType(int value) {
        this.value = value;
    }
}
