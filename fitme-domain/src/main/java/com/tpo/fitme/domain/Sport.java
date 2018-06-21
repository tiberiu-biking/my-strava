package com.tpo.fitme.domain;

import lombok.Getter;

/**
 * @author Tiberiu
 * @since 19.06.18
 */
@Getter
public enum Sport {

    HIKE("Hiking"),
    ALPINESKI("Alpine Ski"),
    RIDE("Cycling"),
    ROAD("Road Cycling"),
    RUN("Running"),
    SWIM("Swimming"),
    MTB("Mountain Biking");

    private final String name;

    Sport(String name) {
        this.name = name;
    }
}
