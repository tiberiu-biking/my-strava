package com.tpo.fitme.service;

import com.tpo.fitme.service.sync.AsyncSynchronizer;
import com.tpo.fitme.service.sync.Synchronizer;
import com.tpo.fitme.strava.client.StravaClientConfig;
import com.tpo.fitme.strava.client.rest.ActivityRestClient;
import com.tpo.strava.persistence.PersistenceConfiguration;
import com.tpo.strava.persistence.service.ActivityService;
import org.springframework.context.annotation.*;
import org.springframework.scheduling.annotation.EnableAsync;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_SINGLETON;

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
    @Scope(scopeName = SCOPE_SINGLETON)
    public Synchronizer stravaSynchronizer(ActivityRestClient activityRestClient,
                                           ActivityService activityService) {
        return new AsyncSynchronizer(activityRestClient, activityService);
    }

}
