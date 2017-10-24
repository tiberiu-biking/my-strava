package com.tpo.strava.gui.main;

import com.tpo.fitness.service.athlete.activity.AthleteService;
import com.tpo.strava.gui.event.DashboardEventBus;
import com.tpo.strava.gui.view.dashboard.DashboardMenu;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.SpringViewDisplay;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Tiberiu on 24/10/15.
 */
@Theme("dashboard")
@Title("My Strava")
@SpringUI
@SpringViewDisplay
public class MyFitnessUI extends UI implements ViewDisplay {

    private final DashboardEventBus dashboardEventbus = new DashboardEventBus();
    private Panel viewDisplay;

    @Autowired
    private AthleteService athleteService;

    public static DashboardEventBus getDashboardEventbus() {
        return ((MyFitnessUI) getCurrent()).dashboardEventbus;
    }

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        addStyleName(ValoTheme.UI_WITH_MENU);
        DashboardEventBus.register(this);
        refreshContent();
    }

    private void refreshContent() {
        viewDisplay = new Panel();
        viewDisplay.addStyleName("view-content");
        viewDisplay.setSizeFull();

        HorizontalLayout root = new HorizontalLayout();
        root.setSizeFull();
        root.addStyleName("mainview");
        root.addComponent(new DashboardMenu(athleteService.getAthlete()));
        root.addComponent(viewDisplay);
        root.setExpandRatio(viewDisplay, 1.0f);
        root.setSpacing(false);
        setContent(root);

        getNavigator().navigateTo(getNavigator().getState());
    }

    @Override
    public void showView(View view) {
        viewDisplay.setContent((Component) view);
    }
}
