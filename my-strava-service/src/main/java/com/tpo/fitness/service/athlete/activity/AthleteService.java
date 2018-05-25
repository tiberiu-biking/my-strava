package com.tpo.fitness.service.athlete.activity;

import com.tpo.fitness.domain.Athlete;

public interface AthleteService {

    Athlete getAthlete();

    Athlete authenticate(String userName, String password);
}
