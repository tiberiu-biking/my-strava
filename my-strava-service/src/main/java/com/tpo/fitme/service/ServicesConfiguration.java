package com.tpo.fitme.service;

import com.tpo.fitme.strava.client.StravaClientConfig;
import com.tpo.strava.persistence.PersistenceConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
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

}
