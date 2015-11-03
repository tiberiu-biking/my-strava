package com.tpo.strava.service.strava.gui.main;

import com.tpo.strava.service.activity.ActivityService;
import com.tpo.strava.service.athlete.AthleteService;
import com.tpo.strava.service.domain.ActivitiesSummary;
import com.tpo.strava.service.strava.gui.chart.CaloriesChartView;
import com.tpo.strava.service.strava.gui.chart.DistanceChartView;
import com.tpo.strava.service.strava.gui.user.ConnectPanel;
import com.tpo.strava.service.strava.gui.user.UserPanel;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalSplitPanel;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Tiberiu on 24/10/15.
 */
@SpringUI
@Theme("valo")
public class MyVaadinUI extends UI {

    @Autowired
    private ActivityService activityService;

    @Autowired
    private AthleteService athleteService;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        String authToken = vaadinRequest.getParameter("authToken");

        VerticalSplitPanel mainPanel = new VerticalSplitPanel();

        if (authToken != null) {
            mainPanel.setFirstComponent(new UserPanel(athleteService, authToken));
            List<ActivitiesSummary> activities = activityService.getActivitiesSummary(authToken);

            VerticalSplitPanel chartPanel = new VerticalSplitPanel();
            chartPanel.setFirstComponent(new CaloriesChartView("Calories", activities));
            chartPanel.setSecondComponent(new DistanceChartView("Distances", activities));
            mainPanel.setSecondComponent(chartPanel);
            mainPanel.setSplitPosition(4);
            setContent(mainPanel);
        } else {
            setContent(new ConnectPanel());
        }
    }
}
