package com.tpo.strava.gui.main;

import com.google.common.eventbus.Subscribe;
import com.tpo.fitness.domain.Athlete;
import com.tpo.fitness.service.athlete.activity.AthleteService;
import com.tpo.strava.gui.event.DashboardEvent;
import com.tpo.strava.gui.event.DashboardEventBus;
import com.tpo.strava.gui.view.login.LoginScreen;
import com.tpo.strava.gui.view.main.MainScreen;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.server.Page;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

/**
 * Created by Tiberiu on 24/10/15.
 */
@Theme("dashboard")
@Title("My Strava")
@SpringUI
public class MyFitnessUI extends UI {

    private final DashboardEventBus dashboardEventbus = new DashboardEventBus();

    @Autowired
    private AthleteService athleteService;

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        Responsive.makeResponsive(this);
        //getNavigator().addView(VIEW_NAME, LoginView.class);
        addStyleName(ValoTheme.UI_WITH_MENU);
        DashboardEventBus.register(this);
        refreshContent();
    }

    @Subscribe
    public void userLoginRequested(final DashboardEvent.UserLoginRequestedEvent event) {
        Athlete user = athleteService.authenticate(event.getUserName(), event.getPassword());
        VaadinSession.getCurrent().setAttribute(Athlete.class.getName(), user);
        refreshContent();
    }

    @Subscribe
    public void userLoggedOut(final DashboardEvent.UserLoggedOutEvent event) {
        VaadinSession.getCurrent().close();
        Page.getCurrent().reload();
    }

    @Subscribe
    public void closeOpenWindows(final DashboardEvent.CloseOpenWindowsEvent event) {
        for (Window window : getWindows()) {
            window.close();
        }
    }

    private void refreshContent() {
        Athlete user = (Athlete) VaadinSession.getCurrent().getAttribute(Athlete.class.getName());
        if (user != null) {
            setContent(applicationContext.getBean(MainScreen.class));
        } else {
            setContent(applicationContext.getBean(LoginScreen.class));
        }
    }

    public static DashboardEventBus getDashboardEventbus() {
        return ((MyFitnessUI) getCurrent()).dashboardEventbus;
    }

}
