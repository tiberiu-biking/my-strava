package com.tpo.strava.gui.navigator;

import com.tpo.strava.gui.view.DashboardView;
import com.tpo.strava.gui.view.chart.CaloriesChartView;
import com.tpo.strava.gui.view.chart.DistanceChartView;
import com.tpo.strava.gui.view.grid.ActivityGridView;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.server.Resource;

public enum DashboardViewType {

    DASHBOARD(DashboardView.VIEW_NAME, DashboardView.class, VaadinIcons.HOME, "Dashboard"),
    CALORIES(CaloriesChartView.VIEW_NAME, CaloriesChartView.class, VaadinIcons.FIRE, "Calories"),
    DISTANCES(DistanceChartView.VIEW_NAME, DistanceChartView.class, VaadinIcons.BAR_CHART, "Distances"),
    WORKOUTS(ActivityGridView.VIEW_NAME, ActivityGridView.class, VaadinIcons.GRID, "Workouts");

    private final String viewName;
    private final Class<? extends View> viewClass;
    private final Resource icon;
    private final String caption;

    DashboardViewType(final String viewName, Class<? extends View> viewClass, final Resource icon, String caption) {
        this.viewName = viewName;
        this.viewClass = viewClass;
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

    public Class<? extends View> getViewClass() {
        return viewClass;
    }

    public static DashboardViewType getByViewName(final String viewName) {
        DashboardViewType result = null;
        for (DashboardViewType viewType : values()) {
            if (viewType.getViewName().equals(viewName)) {
                result = viewType;
                break;
            }
        }
        return result;
    }

}
