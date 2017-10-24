package com.tpo.strava.gui.view.chart;

import at.downdrown.vaadinaddons.highchartsapi.model.data.HighChartsData;
import at.downdrown.vaadinaddons.highchartsapi.model.data.base.StringDoubleData;
import com.tpo.fitness.domain.ActivitiesSummary;
import com.tpo.fitness.service.activity.ActivitiesService;
import com.tpo.strava.gui.component.chart.StravaChart;
import com.vaadin.spring.annotation.SpringView;

import javax.annotation.PostConstruct;
import java.util.List;

@SpringView(name = CaloriesChartView.VIEW_NAME)
public class CaloriesChartView extends AbstractActivitiesView {

    public static final String VIEW_NAME = "calories";

    public CaloriesChartView(ActivitiesService activitiesService) {
        super(activitiesService);
    }

    @PostConstruct
    public void init() {
        addComponent(buildChart(activitiesService.getSummary()));
    }

    private StravaChart buildChart(final List<ActivitiesSummary> activitiesSummaries) {
        return new StravaChart("Calories", activitiesSummaries) {
            @Override
            protected HighChartsData getColumnValue(ActivitiesSummary activity) {
                return new StringDoubleData(activity.getPeriod(), activity.getCalories());
            }
        };
    }


}
