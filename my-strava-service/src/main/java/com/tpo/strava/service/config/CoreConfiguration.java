package com.tpo.strava.service.config;

import com.tpo.strava.service.properties.AppProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * Created by Tiberiu on 28/11/15.
 */
@Configuration
public class CoreConfiguration {

    @Bean
    public AppProperties appProperties(Environment environment) {
        return new AppProperties(environment);
    }
}
