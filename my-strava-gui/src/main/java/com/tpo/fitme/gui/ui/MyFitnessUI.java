package com.tpo.fitme.gui.ui;

import com.tpo.strava.gui.view.login.LoginScreen;
import com.tpo.fitme.gui.domain.UserSession;
import com.tpo.strava.gui.view.main.MainScreen;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

@Title("Harden the fuck up!")
@Theme("dashboard")
@SpringUI
public class MyFitnessUI extends UI {

    @Autowired
    private LoginScreen loginScreen;

    @Autowired
    private MainScreen mainScreen;

    @Autowired
    private UserSession userSession;

    @Override
    protected void init(VaadinRequest request) {
        Responsive.makeResponsive(this);
        addStyleName(ValoTheme.UI_WITH_MENU);

        if (!userSession.isLoggedIn()) {
            addWindow(loginScreen);
        } else {
            mainScreen.refresh();
            setContent(mainScreen);
        }
    }
}
