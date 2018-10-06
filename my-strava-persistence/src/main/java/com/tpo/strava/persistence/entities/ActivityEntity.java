package com.tpo.strava.persistence.entities;

import com.tpo.fitme.domain.Sport;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity(name = "activities")
public class ActivityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String externalId;

    @Column
    private Long athleteId;

    @Column
    private Long calories;

    @Column
    private Float distance;

    @Column
    private Long insertDate;

    @Column
    private LocalDateTime startDate;

    @Column
    private String name;

    @Column
    private Float elevation;

    @Column
    private Long duration;

    @Column
    @Enumerated(value = EnumType.STRING)
    private Sport sport;
}
