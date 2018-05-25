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
@SpringView(name = GearGridView.VIEW_NAME)
public class GearGridView extends AbstractActivitiesView {

    public static final String VIEW_NAME = "gears";

    public GearGridView(ActivitiesService uiActivitiesService) {
        super(uiActivitiesService);
        Grid<Activity> grid = buildGrid();
        addComponent(grid);
    }

    private Grid<Activity> buildGrid() {
        Grid<Activity> grid = createGrid();
        grid.setItems(activitiesService.getAllInChronologicalOrder());
        grid.addColumn(Activity::getName).setCaption("Name").setHidable(false);
        grid.addColumn(Activity::getType).setCaption("Type").setHidable(false);
        grid.addColumn(Activity::getDistance).setCaption("Distance(km)").setHidable(true);
        return grid;
    }

    private Grid<Activity> createGrid() {
        Grid<Activity> grid = new Grid<>();
        grid.setSizeFull();
        grid.setSelectionMode(Grid.SelectionMode.SINGLE);
        return grid;
    }

}
