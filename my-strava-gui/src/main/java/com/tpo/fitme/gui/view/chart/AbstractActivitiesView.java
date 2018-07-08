package com.tpo.fitme.gui.view.chart;

import com.tpo.strava.persistence.service.ActivityService;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;

import static com.vaadin.server.Sizeable.Unit.PERCENTAGE;

/**
 * @author Tiberiu
 * @since 04/12/15.
 */
public abstract class AbstractActivitiesView extends VerticalLayout implements View {

    protected ActivityService activityService;

    @Autowired
    public AbstractActivitiesView(ActivityService activityService) {
        this.activityService = activityService;
        initUI();
    }

    private void initUI() {
        setMargin(false);
        setSpacing(false);
        setHeight(100.0f, PERCENTAGE);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }


}
