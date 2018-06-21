package com.tpo.strava.persistence.service.repository.repository;

import com.tpo.fitme.domain.Athlete;

/**
 * @author Tiberiu
 * @since 06.10.17
 */
public interface AthleteRepository {

    void save(Athlete athlete);

    boolean exists(String id);

    Athlete fineOne(String id);

    void updateAuthToken(Athlete athlete);
}
