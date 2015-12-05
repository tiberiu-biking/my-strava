package com.tpo.strava.gui.navigator;

import com.tpo.strava.gui.view.chart.CaloriesChartView;
import com.tpo.strava.gui.view.chart.DistanceChartView;
import com.tpo.strava.gui.view.dashboard.DashboardView;
import com.vaadin.navigator.View;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Resource;

public enum DashboardViewType {

    DASHBOARD("dashboard", DashboardView.class, FontAwesome.HOME, true),
    CALORIES("calories", CaloriesChartView.class, FontAwesome.BAR_CHART_O, true),
    DISTANCES("distances", DistanceChartView.class, FontAwesome.BAR_CHART_O, true);

    private final String viewName;
    private final Class<? extends View> viewClass;
    private final Resource icon;
    private final boolean stateful;

    DashboardViewType(final String viewName,
                      final Class<? extends View> viewClass, final Resource icon,
                      final boolean stateful) {
        this.viewName = viewName;
        this.viewClass = viewClass;
        this.icon = icon;
        this.stateful = stateful;
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

    public boolean isStateful() {
        return stateful;
    }

    public String getViewName() {
        return viewName;
    }

    public Class<? extends View> getViewClass() {
        return viewClass;
    }

    public Resource getIcon() {
        return icon;
    }

}
