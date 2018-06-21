package com.tpo.fitme.gui.view.dashboard;

import com.tpo.fitme.domain.Sport;
import com.tpo.fitme.gui.component.textfield.ReadOnlyTextField;
import com.tpo.fitme.gui.constants.ThemedIcon;
import com.tpo.fitme.service.statistics.StatisticsService;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static java.lang.String.valueOf;

/**
 * @author Tiberiu
 * @since 13.06.18
 */
@SpringComponent
public class ThisYearPanel extends HorizontalLayout {

    private final StatisticsService statisticsService;

    @Autowired
    public ThisYearPanel(StatisticsService statisticsService) {
        super();
        this.statisticsService = statisticsService;
    }

    @PostConstruct
    public void init() {
        setVisuals();

        FormLayout details = buildRoot();
        addComponentsAndExpand(details);

        details.addComponent(buildTitleLabel());
        LocalDateTime startOfYear = LocalDateTime.now().with(TemporalAdjusters.firstDayOfYear());

        details.addComponent(buildField(Sport.ROAD, statisticsService.getTotalDistance(Sport.ROAD, startOfYear), ThemedIcon.ROAD));
        details.addComponent(buildField(Sport.MTB, statisticsService.getTotalDistance(Sport.MTB, startOfYear), ThemedIcon.MTB));
        details.addComponent(buildField(Sport.ALPINESKI, statisticsService.getTotalDistance(Sport.ALPINESKI, startOfYear), ThemedIcon.SKI));
        details.addComponent(buildField(Sport.HIKE, statisticsService.getTotalDistance(Sport.HIKE, startOfYear), ThemedIcon.HIKE));
    }

    private FormLayout buildRoot() {
        FormLayout details = new FormLayout();
        details.addStyleName(ValoTheme.FORMLAYOUT_LIGHT);
        return details;
    }

    private Label buildTitleLabel() {
        Label section = new Label("This Year");
        section.addStyleName(ValoTheme.LABEL_H3);
        section.addStyleName(ValoTheme.LABEL_COLORED);
        return section;
    }

    private ReadOnlyTextField buildField(Sport sport, float value, ThemedIcon icon) {
        return new ReadOnlyTextField(sport.getName(), valueOf(value) + " km", icon);
    }

    private void setVisuals() {
        setCaption("All-Time");
        setWidth(100.0f, Unit.PERCENTAGE);
        setMargin(true);
    }
}
