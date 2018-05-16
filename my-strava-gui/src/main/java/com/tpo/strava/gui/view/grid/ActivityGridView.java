package com.tpo.strava.gui.view.grid;

import com.tpo.fitness.domain.activity.Activity;
import com.tpo.fitness.service.activity.ActivitiesService;
import com.tpo.strava.gui.view.chart.AbstractActivitiesView;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Grid;

/**
 * @author Tiberiu
 * @since 14.10.17
 */
@SpringView(name = ActivityGridView.VIEW_NAME)
public class ActivityGridView extends AbstractActivitiesView {

    public static final String VIEW_NAME = "activities";

    public ActivityGridView(ActivitiesService uiActivitiesService) {
        super(uiActivitiesService);
        Grid<Activity> grid = buildGrid();
        addComponent(grid);
    }

    private Grid<Activity> buildGrid() {
        Grid<Activity> grid = new Grid<>();
        grid.setSizeFull();
        grid.setSelectionMode(Grid.SelectionMode.SINGLE);
        grid.setItems(activitiesService.getAllInChronologicalOrder());
        grid.addColumn(Activity::getName).setCaption("Name");
        grid.addColumn(Activity::getType).setCaption("Type");
        grid.addColumn(Activity::getCalories).setCaption("Calories");
        grid.addColumn(Activity::getDuration).setCaption("Duration(minutes)");
        grid.addColumn(Activity::getDistance).setCaption("Distance(km)");
        grid.addColumn(Activity::getElevation).setCaption("Elevation(meters)");
        grid.addColumn(Activity::getStartDate).setCaption("Date");
        return grid;
    }

}
