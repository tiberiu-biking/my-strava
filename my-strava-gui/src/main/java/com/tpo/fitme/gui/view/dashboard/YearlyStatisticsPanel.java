package com.tpo.fitme.gui.view.dashboard;

import com.tpo.fitme.domain.Sport;
import com.tpo.fitme.gui.component.textfield.ReadOnlyTextField;
import com.tpo.fitme.gui.constants.SportIcon;
import com.tpo.fitme.gui.domain.UserSession;
import com.tpo.fitme.service.statistics.StatisticsService;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.themes.ValoTheme;

import javax.annotation.PostConstruct;
import java.util.Arrays;

import static java.lang.String.valueOf;

/**
 * @author Tiberiu
 * @since 13.06.18
 */
abstract class YearlyStatisticsPanel extends HorizontalLayout {
    abstract int getYear();

    abstract String getTitle();

    private final Long athleteId;
    private final StatisticsService statisticsService;

    public YearlyStatisticsPanel(UserSession userSession, StatisticsService statisticsService) {
        super();
        this.athleteId = userSession.getUser().getId();
        this.statisticsService = statisticsService;
    }

    @PostConstruct
    public void init() {
        setVisuals();

        FormLayout details = buildRoot();
        details.addComponent(buildTitleLabel());
        addStatisticsPanels(details);
        addComponentsAndExpand(details);
    }

    private void addStatisticsPanels(FormLayout details) {
        Arrays.stream(Sport.values()).forEach(sport -> {

            float value;
            if (Sport.Unit.KM.equals(sport.getUnit())) {
                value = statisticsService.getTotalDistance(athleteId, sport, getYear());
            } else {
                value = statisticsService.getTotalDuration(athleteId, sport, getYear());
            }
            if (value > 0) {
                details.addComponent(buildField(sport, value, SportIcon.forSport(sport), sport.getUnit().getUnit()));

            }
        });
    }

    private FormLayout buildRoot() {
        FormLayout details = new FormLayout();
        details.addStyleName(ValoTheme.FORMLAYOUT_LIGHT);
        return details;
    }

    private Label buildTitleLabel() {
        Label section = new Label(getTitle());
        section.addStyleName(ValoTheme.LABEL_H3);
        section.addStyleName(ValoTheme.LABEL_COLORED);
        return section;
    }

    private ReadOnlyTextField buildField(Sport sport, float value, SportIcon icon, String unit) {
        return new ReadOnlyTextField(sport.getCaption(), valueOf(value) + " " + unit, icon);
    }

    private void setVisuals() {
        setCaption(getTitle());
        setWidth(100.0f, Unit.PERCENTAGE);
        setMargin(true);
    }
}
