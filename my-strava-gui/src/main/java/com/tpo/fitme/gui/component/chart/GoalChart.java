package com.tpo.fitme.gui.component.chart;

import at.downdrown.vaadinaddons.highchartsapi.HighChart;
import at.downdrown.vaadinaddons.highchartsapi.HighChartFactory;
import at.downdrown.vaadinaddons.highchartsapi.exceptions.HighChartsException;
import at.downdrown.vaadinaddons.highchartsapi.model.Axis;
import at.downdrown.vaadinaddons.highchartsapi.model.ChartConfiguration;
import at.downdrown.vaadinaddons.highchartsapi.model.ChartType;
import at.downdrown.vaadinaddons.highchartsapi.model.data.base.IntData;
import at.downdrown.vaadinaddons.highchartsapi.model.plotoptions.BarChartPlotOptions;
import at.downdrown.vaadinaddons.highchartsapi.model.series.BarChartSeries;
import com.tpo.fitme.gui.constants.CustomColors;
import com.tpo.strava.persistence.service.utils.Calendar;
import com.vaadin.ui.VerticalLayout;

import java.time.LocalDateTime;
import java.util.Arrays;

import static java.util.Collections.singletonList;

/**
 * Created by Tiberiu on 29/10/15.
 */
public class GoalChart extends VerticalLayout {

    public GoalChart(int activeDaysThisYear, int activeDaysLastYear) {
        setSizeFull();
        HighChart chart = createChart(activeDaysThisYear, activeDaysLastYear);
        addComponentsAndExpand(chart);
    }


    private HighChart createChart(int activeDays, int activeDaysLastYear) {
        ChartConfiguration chartConfig = new ChartConfiguration();
        chartConfig.setChartType(ChartType.BAR);
        chartConfig.setTitle("Active Days");
        chartConfig.setTooltipEnabled(false);
        chartConfig.setColors(Arrays.asList(CustomColors.BLUE, CustomColors.ORANGE, CustomColors.RAPHA_PINK));

        // axis
        Axis xAxis = new Axis(Axis.AxisType.xAxis);
        xAxis.setAxisValueType(Axis.AxisValueType.CATEGORY);
        xAxis.setLabelsEnabled(false);

        Axis yAxis = new Axis(Axis.AxisType.yAxis);
        yAxis.setTitle("Active Days");
        yAxis.setAxisValueType(Axis.AxisValueType.CATEGORY);
        yAxis.setLabelsEnabled(false);

        chartConfig.setxAxis(xAxis);
        chartConfig.setyAxis(yAxis);

        // plot
        BarChartPlotOptions plotOptions = new BarChartPlotOptions();
        plotOptions.setAnimated(true);
        plotOptions.setDataLabelsEnabled(true);
        chartConfig.setPlotOptions(plotOptions);

        // series
        chartConfig.getSeriesList().add(new BarChartSeries("Days gone", singletonList(new IntData(Calendar.dayOfYear()))));
        chartConfig.getSeriesList().add(new BarChartSeries(String.valueOf(Calendar.year()), singletonList(new IntData(activeDays))));
        chartConfig.getSeriesList().add(new BarChartSeries(String.valueOf(LocalDateTime.now().getYear() - 1), singletonList(new IntData(activeDaysLastYear))));


        HighChart columnChart = null;
        try {
            columnChart = HighChartFactory.renderChart(chartConfig);
            columnChart.setSizeFull();
        } catch (HighChartsException e) {
            e.printStackTrace();
        }
        return columnChart;
    }

}
