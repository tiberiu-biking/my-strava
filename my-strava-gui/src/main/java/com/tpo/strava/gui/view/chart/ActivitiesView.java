package com.tpo.strava.gui.view.chart;

import com.tpo.fitness.domain.ActivitiesSummary;
import com.tpo.fitness.domain.activity.Activity;
import com.tpo.strava.gui.main.MyVaadinUI;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.VerticalLayout;

import java.util.List;

/**
 * @author Tiberiu
 * @since 04/12/15.
 */
public class ActivitiesView extends VerticalLayout implements View {

    public ActivitiesView() {
        setMargin(false);
        setSpacing(false);
        setHeight(100.0f, Unit.PERCENTAGE);
    }

    protected List<ActivitiesSummary> getActivitiesSummary() {
        return MyVaadinUI.getActivityService().getSummary();
    }

    protected List<Activity> getActivities() {
        return MyVaadinUI.getActivityService().getAll();
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }


}
