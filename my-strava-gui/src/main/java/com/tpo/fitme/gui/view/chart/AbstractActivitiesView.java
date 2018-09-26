package com.tpo.fitme.gui.view.chart;

import com.tpo.fitme.gui.view.grid.AbstractGridView;
import com.tpo.strava.persistence.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Tiberiu
 * @since 04/12/15.
 */
public abstract class AbstractActivitiesView extends AbstractGridView {

    protected ActivityService activityService;

    @Autowired
    public AbstractActivitiesView(ActivityService activityService) {
        this.activityService = activityService;
    }

}
