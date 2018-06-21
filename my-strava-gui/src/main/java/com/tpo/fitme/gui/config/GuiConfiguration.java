package com.tpo.fitme.gui.config;

import com.tpo.fitme.gui.domain.UserSession;
import com.tpo.fitme.service.ServicesConfiguration;
import com.vaadin.spring.annotation.VaadinSessionScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.context.annotation.SessionScope;

/**
 * Created by Tiberiu on 25/10/15.
 */
@Configuration
@Import({ServicesConfiguration.class})
@ComponentScan
public class GuiConfiguration {

    @Bean
    @SessionScope
    @VaadinSessionScope
    public UserSession userSession() {
        return new UserSession();
    }
}
