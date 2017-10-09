package com.tpo.fitness.service.config;

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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by Tiberiu on 28/11/15.
 */
@Configuration
@Import({PersistenceConfiguration.class, StravaProviderConfig.class})
@EnableAsync
@EnableScheduling
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
    public AthleteService uiAthleteService(AthleteRestClient stravaAthleteRestClient) {
        return new UIAthleteService(stravaAthleteRestClient);
    }
}
