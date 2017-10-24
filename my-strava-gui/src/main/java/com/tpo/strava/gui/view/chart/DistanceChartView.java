package com.tpo.strava.gui.view.chart;

import at.downdrown.vaadinaddons.highchartsapi.model.data.HighChartsData;
import at.downdrown.vaadinaddons.highchartsapi.model.data.base.StringIntData;
import com.tpo.fitness.domain.ActivitiesSummary;
import com.tpo.fitness.service.activity.ActivitiesService;
import com.tpo.strava.gui.component.chart.StravaChart;
import com.vaadin.spring.annotation.SpringView;

@SpringView(name = DistanceChartView.VIEW_NAME)
public class DistanceChartView extends AbstractActivitiesView {

    public static final String VIEW_NAME = "distances";

    public DistanceChartView(ActivitiesService activitiesService) {
        super(activitiesService);
        addComponent(buildChart(activitiesService));
    }

    private StravaChart buildChart(ActivitiesService activitiesService) {
        return new StravaChart("Distances", activitiesService.getSummary()) {
            @Override
            protected HighChartsData getColumnValue(ActivitiesSummary activity) {
                return new StringIntData(activity.getPeriod(), activity.getDistance().intValue());
            }
        };
    }

}