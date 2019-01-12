package com.tpo.strava.persistence.service;

import com.tpo.fitme.domain.Athlete;

/**
 * @author Tiberiu
 * @since 06.10.17
 */
public interface AthleteService {

    void save(Athlete athlete);

    Athlete findOne(Long id);

    void updateAuthToken(Athlete athlete);

}
