package com.tpo.config;

import com.tpo.strava.auth.StravaOAuthController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by Tiberiu on 20/10/15.
 */
@Configuration
@EnableWebMvc
public class ControllerConfig {

    @Autowired
    private Environment environment;

    @Bean
    public StravaOAuthController stravaOAuthController() {
        return new StravaOAuthController(environment.getRequiredProperty("strava.oauth.client.secret"));
    }
}
