package com.tpo.strava.persistence;

import com.tpo.strava.persistence.entities.ActivityEntity;
import com.tpo.strava.persistence.repository.ActivityJpaRepository;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by Tiberiu on 23/10/15.
 */
@Configuration
@EnableTransactionManagement
@EntityScan(basePackageClasses = ActivityEntity.class)
@EnableJpaRepositories(basePackageClasses = ActivityJpaRepository.class)
@ComponentScan
public class PersistenceConfiguration {
}
