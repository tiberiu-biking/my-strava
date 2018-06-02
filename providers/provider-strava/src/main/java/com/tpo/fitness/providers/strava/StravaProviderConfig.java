package com.tpo.fitness.providers.strava;

import com.tpo.fitness.providers.api.service.ActivityRestClient;
import com.tpo.fitness.providers.api.service.AthleteRestClient;
import com.tpo.fitness.providers.strava.rest.actitivity.StravaActivityRestClient;
import com.tpo.fitness.providers.strava.rest.athlete.StravaAthleteRestClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Tiberiu
 * @since 08.10.17
 */
@Configuration
public class StravaProviderConfig {

    @Bean
    public ActivityRestClient stravaActivityRestClient() {
        return new StravaActivityRestClient();
    }

    @Bean
    public AthleteRestClient stravaAthleteRestClient() {
        return new StravaAthleteRestClient();
    }

}
