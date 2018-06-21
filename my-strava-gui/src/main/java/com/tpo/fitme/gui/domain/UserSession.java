package com.tpo.fitme.gui.domain;

import com.tpo.fitme.domain.Athlete;
import com.tpo.fitness.service.athlete.activity.AthleteService;
import com.vaadin.spring.annotation.VaadinSessionScope;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

/**
 * @author Tiberiu
 * @since 26.05.18
 */
@Slf4j
@Getter
@VaadinSessionScope
public class UserSession implements Serializable {

    @Autowired
    private AthleteService athleteService;

    private Athlete user;

    public UserSession() {
        log.info("New user session created!");
    }

    public boolean isLoggedIn() {
        return user != null;
    }

    public void login(String token) {
        user = athleteService.login(token);
    }

}
