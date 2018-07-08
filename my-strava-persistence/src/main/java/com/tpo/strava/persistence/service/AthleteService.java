package com.tpo.strava.persistence.service;

import com.tpo.fitme.domain.Athlete;

/**
 * @author Tiberiu
 * @since 06.10.17
 */
public interface AthleteService {

    void save(Athlete athlete);

    boolean exists(String id);

    Athlete fineOne(String id);

    void updateAuthToken(Athlete athlete);
}
