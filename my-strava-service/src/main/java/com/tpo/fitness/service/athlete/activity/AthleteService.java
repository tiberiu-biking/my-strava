package com.tpo.fitness.service.athlete.activity;

import com.tpo.fitness.domain.Athlete;

public interface AthleteService {

    Athlete login(String token);
}
