package com.tpo.fitme.gui.view.chart;

import com.tpo.fitme.gui.domain.UserSession;
import com.tpo.fitme.gui.view.grid.AbstractGridView;
import com.tpo.strava.persistence.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Tiberiu
 * @since 04/12/15.
 */
public abstract class AbstractActivitiesView extends AbstractGridView {

    protected final ActivityService activityService;
    protected final Long athleteId;

    @Autowired
    public AbstractActivitiesView(UserSession userSession, ActivityService activityService) {
        this.activityService = activityService;
        this.athleteId = userSession.getUser().getId();
    }

}
