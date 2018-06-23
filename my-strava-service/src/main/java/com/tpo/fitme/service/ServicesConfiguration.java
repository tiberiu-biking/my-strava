package com.tpo.fitme.service;

import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.oauth.OAuth20Service;
import com.tpo.fitme.service.oauth.StravaOAuth2Api;
import com.tpo.fitme.strava.client.rest.ActivityRestClient;
import com.tpo.fitme.strava.client.rest.AthleteRestClient;
import com.tpo.fitme.strava.client.rest.StravaClientConfig;
import com.tpo.fitness.service.activity.ActivitiesService;
import com.tpo.fitness.service.activity.DefaultActivitiesService;
import com.tpo.fitness.service.athlete.activity.AthleteService;
import com.tpo.fitness.service.athlete.activity.UIAthleteService;
import com.tpo.fitness.service.properties.AppProperties;
import com.tpo.fitness.service.sync.Synchronizer;
import com.tpo.fitness.service.sync.strava.AsyncSynchronizer;
import com.tpo.strava.persistence.PersistenceConfiguration;
import com.tpo.strava.persistence.service.repository.repository.ActivityRepository;
import com.tpo.strava.persistence.service.repository.repository.AthleteRepository;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author Tiberiu
 * @since 05.06.18
 */
@EnableAsync
@ComponentScan
@Import({PersistenceConfiguration.class, StravaClientConfig.class})
@Configuration
public class ServicesConfiguration {

    @Bean
    public AppProperties appProperties(Environment environment) {
        return new AppProperties(environment);
    }

    @Bean
    @Scope(scopeName = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public Synchronizer stravaSynchronizer(ActivityRestClient stravaActivityRestClient,
                                           ActivityRepository activityDatabaseRepository) {
        return new AsyncSynchronizer(stravaActivityRestClient, activityDatabaseRepository);
    }

    @Bean
    public ActivitiesService uiActivitiesService(ActivityRepository activityDatabaseRepository) {
        return new DefaultActivitiesService(activityDatabaseRepository);
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
