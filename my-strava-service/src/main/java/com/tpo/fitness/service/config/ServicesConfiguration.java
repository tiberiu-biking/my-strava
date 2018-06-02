package com.tpo.fitness.service.config;

import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.oauth.OAuth20Service;
import com.tpo.fitme.service.oauth.StravaOAuth2Api;
import com.tpo.fitness.providers.api.service.ActivityRestClient;
import com.tpo.fitness.providers.api.service.AthleteRestClient;
import com.tpo.fitness.providers.strava.StravaProviderConfig;
import com.tpo.fitness.service.activity.ActivitiesService;
import com.tpo.fitness.service.activity.UIActivitiesService;
import com.tpo.fitness.service.athlete.activity.AthleteService;
import com.tpo.fitness.service.athlete.activity.UIAthleteService;
import com.tpo.fitness.service.properties.AppProperties;
import com.tpo.fitness.service.sync.Synchronizer;
import com.tpo.fitness.service.sync.strava.StravaSynchronizer;
import com.tpo.strava.persistence.PersistenceConfiguration;
import com.tpo.strava.persistence.service.repository.repository.ActivityDatabaseRepository;
import com.tpo.strava.persistence.service.repository.repository.AthleteRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Created by Tiberiu on 28/11/15.
 */
@Configuration
@Import({PersistenceConfiguration.class, StravaProviderConfig.class})
@EnableAsync
public class ServicesConfiguration {

    @Bean
    public AppProperties appProperties(Environment environment) {
        return new AppProperties(environment);
    }

    @Bean
    public Synchronizer stravaSynchronizer(ActivityRestClient stravaActivityRestClient,
                                           ActivityDatabaseRepository activityDatabaseRepository) {
        return new StravaSynchronizer(stravaActivityRestClient, activityDatabaseRepository);
    }

    @Bean
    public ActivitiesService uiActivitiesService(ActivityDatabaseRepository activityDatabaseRepository) {
        return new UIActivitiesService(activityDatabaseRepository);
    }

    @Bean
    public AthleteService uiAthleteService(AthleteRestClient stravaAthleteRestClient, AthleteRepository athleteRepository, Synchronizer stravaSynchronizer) {
        return new UIAthleteService(stravaAthleteRestClient, athleteRepository, stravaSynchronizer);
    }

    @Bean
    public OAuth20Service createService(AppProperties appProperties) {
        StravaOAuth2Api api = new StravaOAuth2Api();
        return new ServiceBuilder(appProperties.getOatuhClientId())
                .apiSecret(appProperties.getOatuhClientSecret())
                .callback(appProperties.getCallbackUrl())
                .scope("public")
                .build(api);
    }

}
