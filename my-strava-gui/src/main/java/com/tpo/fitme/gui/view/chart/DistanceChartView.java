package com.tpo.fitme.gui.view.chart;

import at.downdrown.vaadinaddons.highchartsapi.model.data.HighChartsData;
import at.downdrown.vaadinaddons.highchartsapi.model.data.base.StringIntData;
import com.tpo.fitme.gui.component.chart.StravaChart;
import com.tpo.fitme.service.summary.ActivitiesSummaryService;
import com.tpo.fitness.domain.summary.ActivitiesSummary;
import com.tpo.fitness.domain.summary.Summary;
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
                return new StringIntData(activity.getMonth() + "/" + activity.getYear(), activity.getDistance().intValue());
            }
        };
    }

}