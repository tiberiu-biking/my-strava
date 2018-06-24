package com.tpo.fitme.gui.view.dashboard;

import com.tpo.fitme.domain.Sport;
import com.tpo.fitme.gui.component.textfield.ReadOnlyTextField;
import com.tpo.fitme.gui.constants.ThemedIcon;
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

    private final StatisticsService statisticsService;

    public YearlyStatisticsPanel(StatisticsService statisticsService) {
        super();
        this.statisticsService = statisticsService;
    }

    @PostConstruct
    public void init() {
        setVisuals();

        FormLayout details = buildRoot();
        addComponentsAndExpand(details);

        details.addComponent(buildTitleLabel());

        details.addComponent(buildField(Sport.ROAD, statisticsService.getTotalDistance(Sport.ROAD, getYear()), ThemedIcon.ROAD));
        details.addComponent(buildField(Sport.MTB, statisticsService.getTotalDistance(Sport.MTB, getYear()), ThemedIcon.MTB));
        details.addComponent(buildField(Sport.ALPINESKI, statisticsService.getTotalDistance(Sport.ALPINESKI, getYear()), ThemedIcon.SKI));
        details.addComponent(buildField(Sport.HIKE, statisticsService.getTotalDistance(Sport.HIKE, getYear()), ThemedIcon.HIKE));
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

    private ReadOnlyTextField buildField(Sport sport, float value, ThemedIcon icon) {
        return new ReadOnlyTextField(sport.getName(), valueOf(value) + " km", icon);
    }

    private void setVisuals() {
        setCaption(getTitle());
        setWidth(100.0f, Unit.PERCENTAGE);
        setMargin(true);
    }
}
