package com.tpo.fitme.gui.view.dashboard;

import com.tpo.fitme.domain.Sport;
import com.tpo.fitme.gui.component.textfield.ReadOnlyTextField;
import com.tpo.fitme.gui.constants.ThemedIcon;
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
        details.addComponent(buildField(Sport.ROAD, statisticsService.getTotalDistance(athleteId, Sport.ROAD), ThemedIcon.ROAD, " km"));
        details.addComponent(buildField(Sport.MTB, statisticsService.getTotalDistance(athleteId, Sport.MTB), ThemedIcon.MTB, " km"));
        details.addComponent(buildField(Sport.ALPINESKI, statisticsService.getTotalDistance(athleteId, Sport.ALPINESKI), ThemedIcon.SKI, " km"));
        details.addComponent(buildField(Sport.HIKE, statisticsService.getTotalDistance(athleteId, Sport.HIKE), ThemedIcon.HIKE, " km"));
        details.addComponent(buildField(Sport.SOCCER, statisticsService.getTotalDuration(athleteId, Sport.SOCCER), ThemedIcon.SOCCER, " minutes"));
        details.addComponent(buildField(Sport.RUN, statisticsService.getTotalDistance(athleteId, Sport.RUN), ThemedIcon.RUN, " km"));
        details.addComponent(buildField(Sport.TRX, statisticsService.getTotalDuration(athleteId, Sport.TRX), ThemedIcon.TRX, " minutes"));
        details.addComponent(buildField(Sport.HIIT, statisticsService.getTotalDuration(athleteId, Sport.HIIT), ThemedIcon.HIIT, " minutes"));
        details.addComponent(buildField(Sport.YOGA, statisticsService.getTotalDuration(athleteId, Sport.YOGA), ThemedIcon.YOGA, " minutes"));
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

    private ReadOnlyTextField buildField(Sport sport, float value, ThemedIcon icon, String unit) {
        return new ReadOnlyTextField(sport.getName(), valueOf(value) + unit, icon);
    }

    private void setVisuals() {
        setCaption("All-Time");
        setWidth(100.0f, Unit.PERCENTAGE);
        setMargin(true);
    }
}
