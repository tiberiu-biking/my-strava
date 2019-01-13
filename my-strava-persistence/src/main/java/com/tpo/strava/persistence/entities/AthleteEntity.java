package com.tpo.strava.persistence.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Tiberiu Popa
 * @since 6/2/2018
 */
@Getter
@Setter
@Entity(name = "athletes")
public class AthleteEntity {

    @Id
    @Column
    private Long id;

    @Column
    private String username;

    @Column
    private String name;

    @Column
    private String authToken;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "athlete_id")
    private List<GearEntity> gears = new ArrayList<>();
}
