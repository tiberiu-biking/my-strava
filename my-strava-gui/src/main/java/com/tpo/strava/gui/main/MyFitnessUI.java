package com.tpo.strava.gui.main;

import com.tpo.strava.gui.view.login.LoginScreen;
import com.tpo.strava.gui.view.login.UserSession;
import com.tpo.strava.gui.view.main.MainScreen;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Tiberiu on 24/10/15.
 */
@Theme("dashboard")
@Title("My Strava")
@SpringUI
public class MyFitnessUI extends UI {

    @Autowired
    private LoginScreen loginWindow;

    @Autowired
    private MainScreen mainScreen;

    @Autowired
    private UserSession userSession;

    @Override
    protected void init(VaadinRequest request) {
        Responsive.makeResponsive(this);
        addStyleName(ValoTheme.UI_WITH_MENU);

        HorizontalLayout layout = new HorizontalLayout();
        layout.setSizeFull();
        setSizeFull();

        setContent(layout);
        if (!userSession.isLoggedIn()) {
            getContent().setVisible(false);
            addWindow(loginWindow);
        } else {
            mainScreen.refresh();
            setContent(mainScreen);
        }
    }
}
