package com.tpo.strava.gui.view.chart;

import at.downdrown.vaadinaddons.highchartsapi.model.data.HighChartsData;
import at.downdrown.vaadinaddons.highchartsapi.model.data.base.StringIntData;
import com.tpo.fitme.service.statistics.ActivitiesSummaryService;
import com.tpo.fitness.domain.summary.ActivitiesSummary;
import com.tpo.fitness.domain.summary.Summary;
import com.tpo.strava.gui.component.chart.StravaChart;
import com.vaadin.spring.annotation.SpringView;

import javax.annotation.PostConstruct;

import static com.tpo.fitme.service.constants.Constants.DEFAULT_CHART_PERIOD;

@SpringView(name = DistanceChartView.VIEW_NAME)
public class DistanceChartView extends AbstractActivitiesSummaryView {

    public static final String VIEW_NAME = "distances";

    public DistanceChartView(ActivitiesSummaryService activitiesSummaryService) {
        super(activitiesSummaryService);
    }

    @PostConstruct
    public void init() {
        addComponent(buildChart(activitiesSummaryService.generateSummarySince(DEFAULT_CHART_PERIOD)));
    }

    private StravaChart buildChart(Summary summary) {
        return new StravaChart("Distances", summary.getActivitiesSummaries()) {
            @Override
            protected HighChartsData getColumnValue(ActivitiesSummary activity) {
                return new StringIntData(activity.getPeriod(), activity.getDistance().intValue());
            }
        };
    }

}