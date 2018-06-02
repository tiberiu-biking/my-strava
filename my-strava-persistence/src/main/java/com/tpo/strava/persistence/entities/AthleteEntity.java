package com.tpo.strava.persistence.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author Tiberiu Popa
 * @since 6/2/2018
 */
@Getter
@Setter
@Entity(name = "athletes")
public class AthleteEntity {

    @Id
    private String id;

    @Column
    private String authToken;
}
