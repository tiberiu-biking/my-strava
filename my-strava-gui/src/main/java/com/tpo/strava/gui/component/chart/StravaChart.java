package com.tpo.strava.gui.component.chart;

import at.downdrown.vaadinaddons.highchartsapi.HighChart;
import at.downdrown.vaadinaddons.highchartsapi.HighChartFactory;
import at.downdrown.vaadinaddons.highchartsapi.exceptions.HighChartsException;
import at.downdrown.vaadinaddons.highchartsapi.model.Axis;
import at.downdrown.vaadinaddons.highchartsapi.model.ChartConfiguration;
import at.downdrown.vaadinaddons.highchartsapi.model.ChartType;
import at.downdrown.vaadinaddons.highchartsapi.model.data.HighChartsData;
import at.downdrown.vaadinaddons.highchartsapi.model.plotoptions.ColumnChartPlotOptions;
import at.downdrown.vaadinaddons.highchartsapi.model.series.ColumnChartSeries;
import com.tpo.fitness.domain.summary.ActivitiesSummary;
import com.vaadin.shared.ui.colorpicker.Color;
import com.vaadin.ui.VerticalLayout;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tiberiu on 29/10/15.
 */
public abstract class StravaChart extends VerticalLayout {

    protected List<ActivitiesSummary> activities;
    private String columnName;

    public StravaChart(String columnName, List<ActivitiesSummary> activities) {
        this.columnName = columnName;
        this.activities = activities;
        HighChart chart = createChart();
        addComponent(chart);
        setExpandRatio(chart, 1);
    }

    protected abstract HighChartsData getColumnValue(ActivitiesSummary activity);

    private HighChart createChart() {
        ChartConfiguration columnConfiguration = new ChartConfiguration();
        columnConfiguration.setTitle(columnName);
        columnConfiguration.setChartType(ChartType.COLUMN);
        columnConfiguration.setLegendEnabled(true);
        columnConfiguration.setCreditsEnabled(true);

        Axis xAxis = new Axis(Axis.AxisType.xAxis);
        xAxis.setAxisValueType(Axis.AxisValueType.DATETIME);


        List<String> xCategories = new ArrayList<>();
        for (ActivitiesSummary activity : activities) {
            DateTime dateTime = activity.getDateTime();
            xCategories.add(dateTime.monthOfYear().getAsShortText() + " " + dateTime.year().getAsString());
        }
        xAxis.setCategories(xCategories);
        columnConfiguration.setxAxis(xAxis);

        ColumnChartPlotOptions plotOptions = new ColumnChartPlotOptions();
        plotOptions.setAnimated(true);
        plotOptions.setDataLabelsEnabled(true);
        columnConfiguration.setPlotOptions(plotOptions);
        ColumnChartSeries column = new ColumnChartSeries(this.columnName, buildColumnValues());
        columnConfiguration.getSeriesList().add(column);

        List<Color> colors = new ArrayList<>();
        colors.add(new Color(0, 127, 182));
//        colors.add(new Color(253, 112, 53));
        columnConfiguration.setColors(colors);

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
