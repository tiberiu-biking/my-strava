package com.tpo.strava.gui.view.chart;

import at.downdrown.vaadinaddons.highchartsapi.model.data.HighChartsData;
import at.downdrown.vaadinaddons.highchartsapi.model.data.base.StringDoubleData;
import com.tpo.fitness.domain.ActivitiesSummary;
import com.tpo.strava.gui.component.chart.StravaChart;

/**
 * Created by Tiberiu on 29/10/15.
 */
public class CaloriesChartView extends ActivitiesView {

    public CaloriesChartView() {

        StravaChart chart = new StravaChart("Calories", getActivitiesSummary()) {
            @Override
            protected HighChartsData getColumnValue(ActivitiesSummary activity) {
                return new StringDoubleData(activity.getPeriod(), activity.getCalories());
            }
        };
        addComponent(chart);
    }


}
