package com.tpo.strava.gui.navigator;

import com.tpo.strava.gui.view.DashboardView;
import com.tpo.strava.gui.view.chart.CaloriesChartView;
import com.tpo.strava.gui.view.chart.DistanceChartView;
import com.tpo.strava.gui.view.grid.ActivityGridView;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Resource;

public enum DashboardViewType {

    HOME(DashboardView.VIEW_NAME, VaadinIcons.HOME, "Dashboard"),
    CALORIES(CaloriesChartView.VIEW_NAME, VaadinIcons.FIRE, "Calories"),
    DISTANCES(DistanceChartView.VIEW_NAME, VaadinIcons.BAR_CHART, "Distances"),
    WORKOUTS(ActivityGridView.VIEW_NAME, VaadinIcons.GRID, "Workouts");

    private final String viewName;
    private final Resource icon;
    private final String caption;

    DashboardViewType(final String viewName, final Resource icon, String caption) {
        this.viewName = viewName;
        this.icon = icon;
        this.caption = caption;
    }

    public String getViewName() {
        return viewName;
    }

    public Resource getIcon() {
        return icon;
    }

    public String getCaption() {
        return caption;
    }

}
