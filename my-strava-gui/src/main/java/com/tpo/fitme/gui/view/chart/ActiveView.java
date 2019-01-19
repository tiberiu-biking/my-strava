package com.tpo.fitme.gui.view.chart;

import com.tpo.fitme.gui.component.chart.GoalChart;
import com.tpo.fitme.gui.domain.UserSession;
import com.tpo.fitme.service.summary.ActivitiesSummaryService;
import com.tpo.strava.persistence.service.utils.Calendar;
import com.vaadin.spring.annotation.SpringView;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * @author Tiberiu
 * @since 2019-01-19
 */
@SpringView(name = ActiveView.VIEW_NAME)
public class ActiveView extends AbstractActivitiesSummaryView {

    public static final String VIEW_NAME = "active";

    @Autowired
    public ActiveView(UserSession userSession, ActivitiesSummaryService activitiesSummaryService) {
        super(userSession, activitiesSummaryService);
    }

    @PostConstruct
    public void init() {
        int activeDaysThisYear = activitiesSummaryService.getActiveDays(athleteId, Calendar.year());
        int activeDaysLastYear = activitiesSummaryService.getActiveDays(athleteId, Calendar.lastYear());
        addComponent(new GoalChart(activeDaysThisYear, activeDaysLastYear));
    }

}
