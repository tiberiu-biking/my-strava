package com.tpo.strava.web.config;

import com.tpo.strava.service.auth.StravaOAuthController;
import com.tpo.strava.service.properties.AppProperties;
import com.tpo.strava.web.controller.core.CloudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by Tiberiu on 20/10/15.
 */
@Configuration
@EnableWebMvc
@EnableAutoConfiguration
public class ControllerConfig {

    @Autowired
    private AppProperties appProperties;

    @Bean
    public StravaOAuthController stravaOAuthController() {
        return new StravaOAuthController(appProperties.getOauthClientSecret(), appProperties.getOatuhClientId());
    }

    @Bean
    public CloudController cloudController() {
        return new CloudController();
    }
}
