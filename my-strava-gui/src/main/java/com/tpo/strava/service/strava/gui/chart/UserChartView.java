package com.tpo.strava.service.strava.gui.chart;

import com.tpo.strava.service.domain.ActivitiesSummary;
import com.tpo.strava.service.domain.Athlete;
import com.tpo.strava.service.strava.gui.main.MyVaadinUI;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.VerticalLayout;

import java.util.List;

/**
 * @author Tiberiu
 * @since 04/12/15.
 */
public class UserChartView extends VerticalLayout implements View {

    protected Athlete getCurrentUser() {
        return (Athlete) VaadinSession.getCurrent().getAttribute(Athlete.class.getName());
    }

    protected String getUserTooken() {
        return getCurrentUser().getToken();
    }

    protected List<ActivitiesSummary> getActivities() {
        return MyVaadinUI.getActivityService().getActivitiesSummary(getUserTooken());
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }


}
