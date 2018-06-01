package com.tpo.strava.gui.view.login;

import com.tpo.fitness.service.config.ServicesConfiguration;
import com.tpo.fitness.service.properties.AppProperties;
import com.vaadin.spring.annotation.VaadinSessionScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.context.annotation.SessionScope;
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

    @Bean
    @SessionScope
    @VaadinSessionScope
    public UserSession userSession() {
        return new UserSession();
    }
}
