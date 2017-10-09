package com.tpo.strava.gui.main;

import com.tpo.fitness.domain.Athlete;
import com.tpo.fitness.service.activity.ActivitiesService;
import com.tpo.fitness.service.athlete.activity.AthleteService;
import com.tpo.strava.gui.event.DashboardEventBus;
import com.tpo.strava.gui.view.MainView;
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
    private ActivitiesService uiActivitiesService;

    @Autowired
    private AthleteService athleteService;

    public static DashboardEventBus getDashboardEventbus() {
        return ((MyVaadinUI) getCurrent()).dashboardEventbus;
    }

    public static ActivitiesService getActivityService() {
        return ((MyVaadinUI) getCurrent()).uiActivitiesService;
    }

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        addStyleName(ValoTheme.UI_WITH_MENU);
        DashboardEventBus.register(this);
        refreshContent();
    }

    private void refreshContent() {
        Athlete athlete = athleteService.getAthlete();
        VaadinSession.getCurrent().setAttribute(Athlete.class.getName(), athlete);
        setContent(new MainView());
        getNavigator().navigateTo(getNavigator().getState());
    }

}
