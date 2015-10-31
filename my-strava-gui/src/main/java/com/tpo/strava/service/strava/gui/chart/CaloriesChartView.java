package com.tpo.strava.service.strava.gui.chart;

import at.downdrown.vaadinaddons.highchartsapi.model.data.HighChartsData;
import at.downdrown.vaadinaddons.highchartsapi.model.data.base.StringDoubleData;
import com.tpo.strava.service.domain.ActivitiesSummary;

import java.util.List;

/**
 * Created by Tiberiu on 29/10/15.
 */
public class CaloriesChartView extends ChartView {

    public CaloriesChartView(String columnName, List<ActivitiesSummary> activities) {
        super(columnName, activities);
    }

    @Override
    protected HighChartsData getColumnValue(ActivitiesSummary activity) {
        return new StringDoubleData(activity.getPeriod(), activity.getCalories());
    }
}
