package com.tpo.strava.gui.view.grid;

import com.tpo.fitness.domain.activity.Activity;
import com.tpo.strava.gui.view.chart.ActivitiesView;
import com.vaadin.ui.Grid;

/**
 * @author Tiberiu
 * @since 14.10.17
 */
public class ActivityGridView extends ActivitiesView {

    public ActivityGridView() {
        Grid<Activity> grid = buildGrid();
        addComponent(grid);
    }

    private Grid<Activity> buildGrid() {
        Grid<Activity> grid = new Grid<>();
        grid.setSizeFull();
        grid.setSelectionMode(Grid.SelectionMode.SINGLE);
        grid.setItems(getActivities());
        grid.addColumn(Activity::getName).setCaption("Name");
        grid.addColumn(Activity::getType).setCaption("Type");
        grid.addColumn(Activity::getCalories).setCaption("Calories");
        grid.addColumn(Activity::getDuration).setCaption("Duration");
        grid.addColumn(Activity::getDistance).setCaption("Distance");
        grid.addColumn(Activity::getElevation).setCaption("Elevation");
        return grid;
    }

}
