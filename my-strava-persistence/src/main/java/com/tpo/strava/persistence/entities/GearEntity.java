package com.tpo.strava.persistence.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Tiberiu
 * @since 12.07.18
 */
@Getter
@Setter
@Entity(name = "gears")
public class GearEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String externalId;

    @Column(name = "athlete_id")
    private Long athleteId;

    @Column
    private String name;

    @Column
    private float distance;

    @Column
    private String brandName;

    @Column
    private String modelName;

    @Column
    private String frameType;

    @Column
    private String description;
}
