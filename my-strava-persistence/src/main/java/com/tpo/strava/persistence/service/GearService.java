package com.tpo.strava.persistence.service;

import com.tpo.fitme.domain.Athlete;
import com.tpo.fitme.domain.Gear;

import java.util.List;

/**
 * @author Tiberiu
 * @since 06.10.17
 */
public interface GearService {

    Gear save(Gear gear);

    List<Gear> findAllByAthlete(Athlete athlete);

    Gear update(Gear gear);
}
