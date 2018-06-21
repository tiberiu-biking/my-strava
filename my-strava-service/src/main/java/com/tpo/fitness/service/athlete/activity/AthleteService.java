package com.tpo.fitness.service.athlete.activity;

import com.tpo.fitme.domain.Athlete;

public interface AthleteService {

    Athlete login(String token);
}
