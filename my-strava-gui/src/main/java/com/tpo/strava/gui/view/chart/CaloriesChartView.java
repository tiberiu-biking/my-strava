package com.tpo.strava.gui.view.chart;

import at.downdrown.vaadinaddons.highchartsapi.model.data.HighChartsData;
import at.downdrown.vaadinaddons.highchartsapi.model.data.base.StringDoubleData;
import com.tpo.strava.gui.component.chart.StravaChart;
import com.tpo.strava.service.domain.ActivitiesSummary;

/**
 * Created by Tiberiu on 29/10/15.
 */
public class CaloriesChartView extends UserChartView {

    public CaloriesChartView() {

        StravaChart chart = new StravaChart("Calories", getActivities()) {
            @Override
            protected HighChartsData getColumnValue(ActivitiesSummary activity) {
                return new StringDoubleData(activity.getPeriod(), activity.getCalories());
            }
        };
        addComponent(chart);
    }


}
