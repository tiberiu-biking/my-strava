package com.tpo.strava.service.strava.gui.chart;

import at.downdrown.vaadinaddons.highchartsapi.HighChart;
import at.downdrown.vaadinaddons.highchartsapi.HighChartFactory;
import at.downdrown.vaadinaddons.highchartsapi.exceptions.HighChartsException;
import at.downdrown.vaadinaddons.highchartsapi.model.ChartConfiguration;
import at.downdrown.vaadinaddons.highchartsapi.model.ChartType;
import at.downdrown.vaadinaddons.highchartsapi.model.data.HighChartsData;
import at.downdrown.vaadinaddons.highchartsapi.model.series.ColumnChartSeries;
import com.tpo.strava.service.domain.ActivitiesSummary;
import com.vaadin.ui.Panel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tiberiu on 29/10/15.
 */
public abstract class ChartView extends Panel {

    private List<ActivitiesSummary> activities;
    private String columnName;

    public ChartView(String columnName, List<ActivitiesSummary> activities) {
        this.columnName = columnName;
        this.activities = activities;
        setContent(createChart());
    }

    protected abstract HighChartsData getColumnValue(ActivitiesSummary activity);

    private HighChart createChart() {
        ChartConfiguration columnConfiguration = new ChartConfiguration();
        columnConfiguration.setTitle(columnName + " Chart");
        columnConfiguration.setChartType(ChartType.COLUMN);
        columnConfiguration.setLegendEnabled(true);

        ColumnChartSeries column = new ColumnChartSeries(this.columnName, buildColumnValues());
        columnConfiguration.getSeriesList().add(column);

        HighChart columnChart = null;
        try {
            columnChart = HighChartFactory.renderChart(columnConfiguration);
            columnChart.setSizeFull();
        } catch (HighChartsException e) {
            e.printStackTrace();
        }
        return columnChart;
    }

    private List<HighChartsData> buildColumnValues() {
        List<HighChartsData> columnValues = new ArrayList<>();

        for (ActivitiesSummary activity : activities) {
            columnValues.add(getColumnValue(activity));
        }
        return columnValues;
    }

}
