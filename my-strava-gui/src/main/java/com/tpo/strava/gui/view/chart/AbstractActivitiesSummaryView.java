package com.tpo.strava.gui.view.chart;

import com.tpo.fitme.service.statistics.ActivitiesSummaryService;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Tiberiu
 * @since 04/12/15.
 */
public abstract class AbstractActivitiesSummaryView extends VerticalLayout implements View {

    protected ActivitiesSummaryService activitiesSummaryService;

    @Autowired
    public AbstractActivitiesSummaryView(ActivitiesSummaryService activitiesSummaryService) {
        this.activitiesSummaryService = activitiesSummaryService;
        setMargin(false);
        setSpacing(false);
        setHeight(100.0f, Unit.PERCENTAGE);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }


}