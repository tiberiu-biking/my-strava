package com.tpo.fitme.gui.view.dashboard;

import com.tpo.fitme.domain.Sport;
import com.tpo.fitme.gui.component.textfield.ReadOnlyTextField;
import com.tpo.fitme.gui.constants.ThemedIcon;
import com.tpo.fitme.gui.domain.UserSession;
import com.tpo.fitme.service.statistics.StatisticsService;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.themes.ValoTheme;

import javax.annotation.PostConstruct;

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
        addComponentsAndExpand(details);

        details.addComponent(buildTitleLabel());

        details.addComponent(buildField(Sport.ROAD, statisticsService.getTotalDistance(athleteId, Sport.ROAD, getYear()), ThemedIcon.ROAD, " km"));
        details.addComponent(buildField(Sport.MTB, statisticsService.getTotalDistance(athleteId, Sport.MTB, getYear()), ThemedIcon.MTB, " km"));
        details.addComponent(buildField(Sport.ALPINESKI, statisticsService.getTotalDistance(athleteId, Sport.ALPINESKI, getYear()), ThemedIcon.SKI, " km"));
        details.addComponent(buildField(Sport.HIKE, statisticsService.getTotalDistance(athleteId, Sport.HIKE, getYear()), ThemedIcon.HIKE, " km"));
        details.addComponent(buildField(Sport.SOCCER, statisticsService.getTotalDuration(athleteId, Sport.SOCCER, getYear()), ThemedIcon.SOCCER, " minutes"));
        details.addComponent(buildField(Sport.RUN, statisticsService.getTotalDistance(athleteId, Sport.RUN, getYear()), ThemedIcon.RUN, " km"));
        details.addComponent(buildField(Sport.TRX, statisticsService.getTotalDuration(athleteId, Sport.TRX, getYear()), ThemedIcon.TRX, " minutes"));
        details.addComponent(buildField(Sport.HIIT, statisticsService.getTotalDuration(athleteId, Sport.HIIT, getYear()), ThemedIcon.HIIT, " minutes"));
        details.addComponent(buildField(Sport.YOGA, statisticsService.getTotalDuration(athleteId, Sport.YOGA, getYear()), ThemedIcon.YOGA, " minutes"));
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

    private ReadOnlyTextField buildField(Sport sport, float value, ThemedIcon icon, String unit) {
        return new ReadOnlyTextField(sport.getName(), valueOf(value) + unit, icon);
    }

    private void setVisuals() {
        setCaption(getTitle());
        setWidth(100.0f, Unit.PERCENTAGE);
        setMargin(true);
    }
}
