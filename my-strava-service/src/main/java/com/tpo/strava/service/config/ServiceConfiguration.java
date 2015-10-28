package com.tpo.strava.service.config;

import com.tpo.strava.service.activity.ActivityService;
import com.tpo.strava.service.activity.StravaActivityService;
import com.tpo.strava.service.athlete.AthleteService;
import com.tpo.strava.service.athlete.StravaAthleteService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Tiberiu on 23/10/15.
 */
@Configuration
public class ServiceConfiguration {

    @Bean
    public ActivityService activityService() {
        return new StravaActivityService();
    }

    @Bean
    public AthleteService athleteService() {
        return new StravaAthleteService();
    }
}
