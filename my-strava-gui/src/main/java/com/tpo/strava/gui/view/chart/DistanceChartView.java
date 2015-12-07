package com.tpo.strava.gui.view.chart;

import at.downdrown.vaadinaddons.highchartsapi.model.data.HighChartsData;
import at.downdrown.vaadinaddons.highchartsapi.model.data.base.StringIntData;
import com.tpo.strava.gui.component.chart.StravaChart;
import com.tpo.strava.service.domain.ActivitiesSummary;

/**
 * Created by Tiberiu on 29/10/15.
 */
public class DistanceChartView extends UserChartView {

    public DistanceChartView() {
        StravaChart chart = new StravaChart("Distances", getActivities()) {
            @Override
            protected HighChartsData getColumnValue(ActivitiesSummary activity) {
                return new StringIntData(activity.getPeriod(), activity.getDistance().intValue());
            }
        };
        addComponent(chart);
    }

}