package com.tpo.fitness.providers.strava;

import com.tpo.fitness.providers.api.service.ActivityRestClient;
import com.tpo.fitness.providers.api.service.AthleteRestClient;
import com.tpo.fitness.providers.strava.rest.actitivity.StravaActivityRestClient;
import com.tpo.fitness.providers.strava.rest.athlete.StravaAthleteRestClient;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.web.client.RestTemplate;

/**
 * @author Tiberiu
 * @since 08.10.17
 */
@Configuration
@EnableRetry
public class StravaProviderConfig {

    @Bean
    public ActivityRestClient stravaActivityRestClient(RestTemplate restTemplate) {
        return new StravaActivityRestClient(restTemplate);
    }

    @Bean
    public AthleteRestClient stravaAthleteRestClient() {
        return new StravaAthleteRestClient();
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder
                .setConnectTimeout(3000)
                .setReadTimeout(3000)
                .build();
    }

}
