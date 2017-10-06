package com.tpo.strava.gui.main;

import com.tpo.strava.gui.event.DashboardEventBus;
import com.tpo.strava.gui.view.MainView;
import com.tpo.strava.gui.view.user.ConnectView;
import com.tpo.strava.service.activity.StravaRemoteActivityService;
import com.tpo.strava.service.athlete.AthleteService;
import com.tpo.strava.service.domain.Athlete;
import com.tpo.strava.service.properties.AppProperties;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Tiberiu on 24/10/15.
 */
@SpringUI
@Theme("dashboard")
@Title("My Strava")
public class MyVaadinUI extends UI {

    private final DashboardEventBus dashboardEventbus = new DashboardEventBus();

    @Autowired
    private StravaRemoteActivityService stravaActivityService;

    @Autowired
    private AthleteService athleteService;

    @Autowired
    private AppProperties appProperties;

    public static DashboardEventBus getDashboardEventbus() {
        return ((MyVaadinUI) getCurrent()).dashboardEventbus;
    }

    public static StravaRemoteActivityService getActivityService() {
        return ((MyVaadinUI) getCurrent()).stravaActivityService;
    }

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        addStyleName(ValoTheme.UI_WITH_MENU);
        DashboardEventBus.register(this);
        refreshContent();
    }

    private void refreshContent() {
//        Athlete athlete = (Athlete) VaadinSession.getCurrent().getAttribute(Athlete.class.getName());
        Athlete athlete = athleteService.getAthlete(appProperties.getOauthToken());
//        Athlete athlete = new Athlete();
        athlete.setToken(appProperties.getOauthToken());

        VaadinSession.getCurrent().setAttribute(Athlete.class.getName(), athlete);

        if (athlete != null) {
            setContent(new MainView());
            getNavigator().navigateTo(getNavigator().getState());
        } else {
            setContent(new ConnectView(appProperties.getOauthUrl(), athleteService));
        }
    }

}
