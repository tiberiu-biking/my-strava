package com.tpo.strava.service.strava.gui.main;

import at.downdrown.vaadinaddons.highchartsapi.HighChart;
import at.downdrown.vaadinaddons.highchartsapi.HighChartFactory;
import at.downdrown.vaadinaddons.highchartsapi.exceptions.HighChartsException;
import at.downdrown.vaadinaddons.highchartsapi.model.ChartConfiguration;
import at.downdrown.vaadinaddons.highchartsapi.model.ChartType;
import at.downdrown.vaadinaddons.highchartsapi.model.data.HighChartsData;
import at.downdrown.vaadinaddons.highchartsapi.model.data.base.StringDoubleData;
import at.downdrown.vaadinaddons.highchartsapi.model.series.ColumnChartSeries;
import com.tpo.strava.service.activity.ActivityService;
import com.tpo.strava.service.athlete.activity.AthleteService;
import com.tpo.strava.service.strava.domain.activity.Activity;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalSplitPanel;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tiberiu on 24/10/15.
 */
@SpringUI
@Theme("valo")
public class MyVaadinUI extends UI {

    @Autowired
    private ActivityService activityService;

    @Autowired
    private AthleteService athleteService;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        VerticalSplitPanel mainPanel = new VerticalSplitPanel();
        mainPanel.setFirstComponent(new Label(athleteService.getAthlete().getFirstname()));
        mainPanel.setSecondComponent(createChart());
        mainPanel.setSplitPosition(10);
        setContent(mainPanel);
    }

    private HighChart createChart() {
        ChartConfiguration columnConfiguration = new ChartConfiguration();
        columnConfiguration.setTitle("Calories chart");
        columnConfiguration.setChartType(ChartType.COLUMN);
        columnConfiguration.setLegendEnabled(true);

        List<HighChartsData> caloriesColumnValues = new ArrayList<>();
        List<Activity> activities = activityService.getActivities();
        for (Activity activity : activities)
            caloriesColumnValues.add(new StringDoubleData(activity.getStart_date(), activity.getCalories()));


        ColumnChartSeries caloriesColumn = new ColumnChartSeries("Calories", caloriesColumnValues);
        columnConfiguration.getSeriesList().add(caloriesColumn);

        HighChart columnChart = null;
        try {
            columnChart = HighChartFactory.renderChart(columnConfiguration);
            columnChart.setSizeFull();
        } catch (HighChartsException e) {
            e.printStackTrace();
        }
        return columnChart;
    }
}
