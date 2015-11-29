package com.tpo.strava.service.strava.gui.main;

import com.google.common.eventbus.Subscribe;
import com.tpo.strava.service.activity.ActivityService;
import com.tpo.strava.service.athlete.AthleteService;
import com.tpo.strava.service.domain.Athlete;
import com.tpo.strava.service.properties.AppProperties;
import com.tpo.strava.service.strava.gui.event.DashboardEvent;
import com.tpo.strava.service.strava.gui.event.DashboardEventBus;
import com.tpo.strava.service.strava.gui.user.ConnectView;
import com.tpo.strava.service.strava.gui.view.MainView;
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
    private ActivityService activityService;

    @Autowired
    private AthleteService athleteService;

    @Autowired
    private AppProperties appProperties;

    public static DashboardEventBus getDashboardEventbus() {
        return ((MyVaadinUI) getCurrent()).dashboardEventbus;
    }

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        addStyleName(ValoTheme.UI_WITH_MENU);
        DashboardEventBus.register(this);
        refreshContent();


//
//        VerticalSplitPanel mainPanel = new VerticalSplitPanel();
//
//        if (authToken != null) {
//            mainPanel.setFirstComponent(new UserPanel(athleteService, authToken));
//            List<ActivitiesSummary> activities = activityService.getActivitiesSummary(authToken);
//
//            VerticalSplitPanel chartPanel = new VerticalSplitPanel();
//            chartPanel.setFirstComponent(new CaloriesChartView("Calories", activities));
//            chartPanel.setSecondComponent(new DistanceChartView("Distances", activities));
//            mainPanel.setSecondComponent(chartPanel);
//            mainPanel.setSplitPosition(4);
//            mainPanel.addComponent(new DashboardView());
//            setContent(mainPanel);
//        } else {
//            setContent(new ConnectView(appProperties.getOauthUrl()));
//        }
    }

    private void refreshContent() {
        Athlete athlete = (Athlete) VaadinSession.getCurrent().getAttribute(Athlete.class.getName());
        if (athlete != null) {
            setContent(new MainView());
            getNavigator().navigateTo(getNavigator().getState());
        } else {
            setContent(new ConnectView(appProperties.getOauthUrl(), athleteService));
        }
    }

    @Subscribe
    public void userLoggedIn(final DashboardEvent.UserLoginRequestedEvent event) {
        refreshContent();
    }

}
