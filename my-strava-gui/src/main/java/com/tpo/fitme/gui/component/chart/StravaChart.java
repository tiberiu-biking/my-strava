package com.tpo.fitme.gui.component.chart;

import at.downdrown.vaadinaddons.highchartsapi.HighChart;
import at.downdrown.vaadinaddons.highchartsapi.HighChartFactory;
import at.downdrown.vaadinaddons.highchartsapi.exceptions.HighChartsException;
import at.downdrown.vaadinaddons.highchartsapi.model.Axis;
import at.downdrown.vaadinaddons.highchartsapi.model.ChartConfiguration;
import at.downdrown.vaadinaddons.highchartsapi.model.ChartType;
import at.downdrown.vaadinaddons.highchartsapi.model.data.HighChartsData;
import at.downdrown.vaadinaddons.highchartsapi.model.plotoptions.ColumnChartPlotOptions;
import at.downdrown.vaadinaddons.highchartsapi.model.series.ColumnChartSeries;
import com.tpo.fitme.domain.summary.ActivitiesSummary;
import com.tpo.fitme.gui.constants.CustomColors;
import com.vaadin.ui.VerticalLayout;

import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static java.util.Collections.singletonList;

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
            LocalDateTime dateTime = activity.getDateTime();
            xCategories.add(dateTime.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH) + " " + dateTime.getYear());
        }
        xAxis.setCategories(xCategories);
        columnConfiguration.setxAxis(xAxis);

        ColumnChartPlotOptions plotOptions = new ColumnChartPlotOptions();
        plotOptions.setAnimated(true);
        plotOptions.setDataLabelsEnabled(true);
        columnConfiguration.setPlotOptions(plotOptions);
        ColumnChartSeries column = new ColumnChartSeries(this.columnName, buildColumnValues());
        columnConfiguration.getSeriesList().add(column);

        columnConfiguration.setColors(singletonList(CustomColors.ORANGE));

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
