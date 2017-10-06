package com.tpo.strava.service.config;

import com.tpo.strava.persistence.PersistenceConfiguration;
import com.tpo.strava.persistence.service.repository.repository.ActivityDatabaseRepository;
import com.tpo.strava.service.activity.StravaRemoteActivityService;
import com.tpo.strava.service.athlete.AthleteService;
import com.tpo.strava.service.athlete.StravaAthleteService;
import com.tpo.strava.service.properties.AppProperties;
import com.tpo.strava.service.sync.StravaSynchronizer;
import com.tpo.strava.service.sync.Synchronizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by Tiberiu on 23/10/15.
 */
@Configuration
//@EnableCaching
@EnableAsync
@EnableScheduling
@Import({PersistenceConfiguration.class, CoreConfiguration.class})
public class ServicesConfiguration {

    @Bean
    public StravaRemoteActivityService stravaActivityService(AppProperties appProperties, ActivityDatabaseRepository activityDatabaseRepository) {
        return new StravaRemoteActivityService(appProperties, activityDatabaseRepository);
    }

    @Bean
    public AthleteService stravaAthleteService() {
        return new StravaAthleteService();
    }

    @Bean
    public Synchronizer stravaSynchronizer(StravaRemoteActivityService stravaActivityService, AppProperties appProperties, ActivityDatabaseRepository activityDatabaseRepository) {
        return new StravaSynchronizer(stravaActivityService, appProperties, activityDatabaseRepository);
    }
}
