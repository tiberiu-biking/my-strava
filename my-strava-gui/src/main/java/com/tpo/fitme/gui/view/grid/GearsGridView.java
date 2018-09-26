package com.tpo.fitme.gui.view.grid;

import com.tpo.fitme.domain.Athlete;
import com.tpo.fitme.domain.Gear;
import com.tpo.fitme.gui.domain.UserSession;
import com.tpo.strava.persistence.service.GearService;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Grid;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Tiberiu
 * @since 14.10.17
 */
@SpringView(name = GearsGridView.VIEW_NAME)
public class GearsGridView extends AbstractGridView {

    public static final String VIEW_NAME = "gears";
    private final GearService gearService;
    private final Athlete athlete;

    @Autowired
    public GearsGridView(GearService gearService, UserSession userSession) {
        this.gearService = gearService;
        this.athlete = userSession.getUser();
        Grid<Gear> grid = buildGrid();
        addComponent(grid);
    }

    private Grid<Gear> buildGrid() {
        Grid<Gear> grid = createGrid();
        grid.setItems(gearService.findAllByAthlete(athlete));
        grid.addColumn(Gear::getName).setCaption("Name").setHidable(false);
        grid.addColumn(Gear::getBrandName).setCaption("Brand").setHidable(false);
        grid.addColumn(Gear::getModelName).setCaption("Model").setHidable(false);
        grid.addColumn(activity -> activity.getDistance().longValue() + " km")
                .setCaption("Distance")
                .setHidable(true);

        return grid;
    }

    private Grid<Gear> createGrid() {
        Grid<Gear> grid = new Grid<>();
        grid.setSizeFull();
        grid.setSelectionMode(Grid.SelectionMode.SINGLE);
        return grid;
    }
}
