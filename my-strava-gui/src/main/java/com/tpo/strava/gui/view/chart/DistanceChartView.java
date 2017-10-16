package com.tpo.strava.gui.view.chart;

import at.downdrown.vaadinaddons.highchartsapi.model.data.HighChartsData;
import at.downdrown.vaadinaddons.highchartsapi.model.data.base.StringIntData;
import com.tpo.fitness.domain.ActivitiesSummary;
import com.tpo.strava.gui.component.chart.StravaChart;

/**
 * Created by Tiberiu on 29/10/15.
 */
public class DistanceChartView extends ActivitiesView {

    public DistanceChartView() {
        StravaChart chart = new StravaChart("Distances", getActivitiesSummary()) {
            @Override
            protected HighChartsData getColumnValue(ActivitiesSummary activity) {
                return new StringIntData(activity.getPeriod(), activity.getDistance().intValue());
            }
        };
        addComponent(chart);
    }

}