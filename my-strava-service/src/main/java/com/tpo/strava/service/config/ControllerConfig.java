package com.tpo.strava.service.config;

import com.tpo.strava.service.controller.StravaOAuthController;
import com.tpo.strava.service.core.CloudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by Tiberiu on 20/10/15.
 */
@Configuration
@EnableWebMvc
@EnableAutoConfiguration
public class ControllerConfig {

    @Autowired
    private Environment environment;

    @Bean
    public StravaOAuthController stravaOAuthController() {
        String clientSecret = environment.getRequiredProperty("strava.oauth.client.secret");
        String clientId = environment.getRequiredProperty("strava.oauth.client.id");
        return new StravaOAuthController(clientSecret, clientId);
    }

    @Bean
    public CloudController cloudController() {
        return new CloudController();
    }
}
