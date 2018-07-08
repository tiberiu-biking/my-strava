package com.tpo.fitme.gui.domain;

import com.tpo.fitme.domain.Athlete;
import com.vaadin.spring.annotation.VaadinSessionScope;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * @author Tiberiu
 * @since 26.05.18
 */
@Slf4j
@Getter
@Setter
@VaadinSessionScope
public class UserSession implements Serializable {

    private Athlete user;

    public boolean isLoggedIn() {
        return user != null;
    }

}
