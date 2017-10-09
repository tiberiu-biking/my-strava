package com.tpo.strava.web.config;

import com.tpo.fitness.service.auth.StravaOAuthController;
import com.tpo.fitness.service.config.ServicesConfiguration;
import com.tpo.fitness.service.properties.AppProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author Tiberiu
 * @since 03.10.17
 */
@Configuration
@EnableWebMvc
@Import({ServicesConfiguration.class})
public class WebConfiguration {

    @Autowired
    private AppProperties appProperties;

    @Bean
    public StravaOAuthController stravaOAuthController() {
        return new StravaOAuthController(appProperties.getOauthClientSecret(), appProperties.getOatuhClientId());
    }
}
