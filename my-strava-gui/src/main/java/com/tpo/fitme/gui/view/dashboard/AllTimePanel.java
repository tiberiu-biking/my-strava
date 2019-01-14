package com.tpo.fitme.gui.view.dashboard;

import com.tpo.fitme.domain.Sport;
import com.tpo.fitme.gui.component.textfield.ReadOnlyTextField;
import com.tpo.fitme.gui.constants.SportIcon;
import com.tpo.fitme.gui.domain.UserSession;
import com.tpo.fitme.service.statistics.StatisticsService;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.Arrays;

import static java.lang.String.valueOf;

/**
 * @author Tiberiu
 * @since 13.06.18
 */
@UIScope
@SpringComponent
public class AllTimePanel extends HorizontalLayout {

    private final StatisticsService statisticsService;
    private final Long athleteId;

    @Autowired
    public AllTimePanel(UserSession userSession, StatisticsService statisticsService) {
        super();
        this.statisticsService = statisticsService;
        this.athleteId = userSession.getUser().getId();
    }

    @PostConstruct
    public void init() {
        setVisuals();
        FormLayout details = buildRoot();
        details.addComponent(buildTitleLabel());
        addStatisticsPanels(details);
    }

    private void addStatisticsPanels(FormLayout details) {
        Arrays.stream(Sport.values()).forEach(sport -> {

            float value;
            if (Sport.Unit.KM.equals(sport.getUnit())) {
                value = statisticsService.getTotalDistance(athleteId, sport);
            } else {
                value = statisticsService.getTotalDuration(athleteId, sport);
            }
            if (value > 0) {
                details.addComponent(buildField(sport, value, SportIcon.forSport(sport), sport.getUnit().getUnit()));

            }
        });
    }

    private FormLayout buildRoot() {
        FormLayout details = new FormLayout();
        details.addStyleName(ValoTheme.FORMLAYOUT_LIGHT);
        addComponentsAndExpand(details);
        return details;
    }

    private Label buildTitleLabel() {
        Label section = new Label("All-Time");
        section.addStyleName(ValoTheme.LABEL_H3);
        section.addStyleName(ValoTheme.LABEL_COLORED);
        return section;
    }

    private ReadOnlyTextField buildField(Sport sport, float value, SportIcon icon, String unit) {
        return new ReadOnlyTextField(sport.getCaption(), valueOf(value) + unit, icon);
    }

    private void setVisuals() {
        setCaption("All-Time");
        setWidth(100.0f, Unit.PERCENTAGE);
        setMargin(true);
    }
}
