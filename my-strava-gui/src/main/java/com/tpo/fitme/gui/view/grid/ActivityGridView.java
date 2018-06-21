package com.tpo.fitme.gui.view.grid;

import com.tpo.fitme.gui.view.chart.AbstractActivitiesView;
import com.tpo.fitness.domain.activity.Activity;
import com.tpo.fitness.service.activity.ActivitiesService;
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
        Grid<Activity> grid = createGrid();
        grid.setItems(activitiesService.getAllInChronologicalOrder());
        grid.addColumn(Activity::getName).setCaption("Name").setHidable(false);
        grid.addColumn(Activity::getSport).setCaption("Type").setHidable(false);
        grid.addColumn(Activity::getCalories).setCaption("Calories").setHidable(false);
        grid.addColumn(Activity::getDuration).setCaption("Duration(minutes)").setHidable(false);
        grid.addColumn(Activity::getDistance).setCaption("Distance(km)").setHidable(true);
        grid.addColumn(Activity::getElevation).setCaption("Elevation(meters)").setHidable(true);
        grid.addColumn(Activity::getStartDate).setCaption("Date").setHidable(true);
        return grid;
    }

    private Grid<Activity> createGrid() {
        Grid<Activity> grid = new Grid<>();
        grid.setSizeFull();
        grid.setSelectionMode(Grid.SelectionMode.SINGLE);
        return grid;
    }

}
