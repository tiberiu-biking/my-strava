package com.tpo.strava.persistence;

import com.tpo.strava.persistence.entities.Activity;
import com.tpo.strava.persistence.repository.ActivityRepository;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by Tiberiu on 23/10/15.
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackageClasses = ActivityRepository.class)
@ComponentScan(basePackageClasses = Activity.class)
public class PersistenceConfiguration {
}
