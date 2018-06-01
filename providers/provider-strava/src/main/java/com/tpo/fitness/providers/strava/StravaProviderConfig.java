package com.tpo.fitness.providers.strava;

import com.tpo.fitness.providers.api.service.ActivityRestClient;
import com.tpo.fitness.providers.api.service.AthleteRestClient;
import com.tpo.fitness.providers.strava.rest.actitivity.StravaActivityRestClient;
import com.tpo.fitness.providers.strava.rest.athlete.StravaAthleteRestClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Tiberiu
 * @since 08.10.17
 */
@Configuration
public class StravaProviderConfig {

    @Value("${strava.oauth.token}")
    private String authToken;

    @Bean
    public ActivityRestClient stravaActivityRestClient() {
        return new StravaActivityRestClient(authToken);
    }

    @Bean
    public AthleteRestClient stravaAthleteRestClient() {
        return new StravaAthleteRestClient();
    }

}
