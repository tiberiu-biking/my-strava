package com.tpo.fitme.gui.view.chart;

import com.tpo.fitme.gui.domain.UserSession;
import com.tpo.fitme.service.summary.ActivitiesSummaryService;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Tiberiu
 * @since 04/12/15.
 */
public abstract class AbstractActivitiesSummaryView extends VerticalLayout implements View {

    final Long athleteId;
    final ActivitiesSummaryService activitiesSummaryService;

    @Autowired
    public AbstractActivitiesSummaryView(UserSession userSession, ActivitiesSummaryService activitiesSummaryService) {
        this.activitiesSummaryService = activitiesSummaryService;
        this.athleteId = userSession.getUser().getId();
        setMargin(false);
        setSpacing(false);
        setHeight(100.0f, Unit.PERCENTAGE);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }


}
