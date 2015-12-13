package com.tpo.strava.service.config;

import com.tpo.strava.service.auth.StravaOAuthController;
import com.tpo.strava.service.core.CloudController;
import com.tpo.strava.service.properties.AppProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by Tiberiu on 20/10/15.
 */
@Configuration
@EnableWebMvc
@EnableAutoConfiguration
@Import(CoreConfiguration.class)
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
