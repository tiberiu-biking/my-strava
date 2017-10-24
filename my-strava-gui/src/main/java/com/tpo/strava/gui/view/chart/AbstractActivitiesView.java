package com.tpo.strava.gui.view.chart;

import com.tpo.fitness.service.activity.ActivitiesService;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Tiberiu
 * @since 04/12/15.
 */
public abstract class AbstractActivitiesView extends VerticalLayout implements View {

    protected ActivitiesService activitiesService;

    @Autowired
    public AbstractActivitiesView(ActivitiesService activitiesService) {
        this.activitiesService = activitiesService;
        setMargin(false);
        setSpacing(false);
        setHeight(100.0f, Unit.PERCENTAGE);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }


}
