package com.tpo.strava.persistence.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity(name = "activities")
public class ActivityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String activityId;

    @Column
    private String athleteId;

    @Column
    private Float calories;

    @Column
    private Float distance;

    @Column
    private Long insertDate;

    @Column
    private Date startDate;

    @Column
    private String name;

    @Column
    private String type;

    @Column
    private Float elevation;

    @Column
    private Integer duration;
}
