package com.tpo.strava.service.strava.gui.chart;

import at.downdrown.vaadinaddons.highchartsapi.model.data.HighChartsData;
import at.downdrown.vaadinaddons.highchartsapi.model.data.base.StringIntData;
import com.tpo.strava.service.domain.ActivitiesSummary;

import java.util.List;

/**
 * Created by Tiberiu on 29/10/15.
 */
public class DistanceChartView extends ChartView {

    public DistanceChartView(String columnName, List<ActivitiesSummary> activities) {
        super(columnName, activities);
    }

    @Override
    protected HighChartsData getColumnValue(ActivitiesSummary activity) {
        return new StringIntData(activity.getPeriod(), activity.getDistance().intValue());
    }
}