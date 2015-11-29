package com.tpo.strava.service.strava.gui.view;

import com.tpo.strava.service.domain.ActivitiesSummary;
import com.tpo.strava.service.domain.Athlete;
import com.tpo.strava.service.strava.gui.chart.CaloriesChartView;
import com.tpo.strava.service.strava.gui.chart.DistanceChartView;
import com.tpo.strava.service.strava.gui.event.DashboardEventBus;
import com.tpo.strava.service.strava.gui.main.MyVaadinUI;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinSession;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.util.List;

@SuppressWarnings("serial")
@SpringView
public final class DashboardView extends Panel implements View {

    public static final String TITLE_ID = "dashboard-title";

    private final VerticalLayout root;
    private final List<ActivitiesSummary> activities;
    private Label titleLabel;
    private CssLayout dashboardPanels;

    public DashboardView() {
        activities = MyVaadinUI.getActivityService().getActivitiesSummary(getCurrentUser().getToken());

        addStyleName(ValoTheme.PANEL_BORDERLESS);
        setSizeFull();
        DashboardEventBus.register(this);

        root = new VerticalLayout();
        root.setSizeFull();
        root.setMargin(true);
        root.addStyleName("dashboard-view");
        setContent(root);
        Responsive.makeResponsive(root);

        root.addComponent(buildHeader());

        root.addComponent(buildSparklines());

        Component content = buildContent();
        root.addComponent(content);
        root.setExpandRatio(content, 1);

    }

    private Component buildSparklines() {
        CssLayout sparks = new CssLayout();
        sparks.addStyleName("sparks");
        sparks.setWidth("100%");
        Responsive.makeResponsive(sparks);

        return sparks;
    }

    private Component buildHeader() {
        HorizontalLayout header = new HorizontalLayout();
        header.addStyleName("viewheader");
        header.setSpacing(true);

        titleLabel = new Label("Strava statistics");
        titleLabel.setId(TITLE_ID);
        titleLabel.setSizeUndefined();
        titleLabel.addStyleName(ValoTheme.LABEL_H4);
        titleLabel.addStyleName(ValoTheme.LABEL_NO_MARGIN);
        header.addComponent(titleLabel);

        return header;
    }

    private Component buildContent() {
        dashboardPanels = new CssLayout();
        dashboardPanels.addStyleName("dashboard-panels");
        Responsive.makeResponsive(dashboardPanels);

        dashboardPanels.addComponent(buildCaloriesChart());
        dashboardPanels.addComponent(buildDistanceChart());

        return dashboardPanels;
    }

    private Component buildCaloriesChart() {
        return createContentWrapper(new CaloriesChartView("Calories", activities));
    }

    private Component buildDistanceChart() {
        return createContentWrapper(new DistanceChartView("Distances", activities));
    }

    private Component createContentWrapper(final Component content) {
        final CssLayout slot = new CssLayout();
        slot.setWidth("100%");
        slot.addStyleName("dashboard-panel-slot");
        slot.addStyleName("max");

        CssLayout card = new CssLayout();
        card.setWidth("100%");
        card.addStyleName(ValoTheme.LAYOUT_CARD);

        Label caption = new Label(content.getCaption());
        caption.addStyleName(ValoTheme.LABEL_H4);
        caption.addStyleName(ValoTheme.LABEL_COLORED);
        caption.addStyleName(ValoTheme.LABEL_NO_MARGIN);
        content.setCaption(null);

        card.addComponents(content);
        slot.addComponent(card);
        return slot;
    }

    private Athlete getCurrentUser() {
        return (Athlete) VaadinSession.getCurrent().getAttribute(Athlete.class.getName());
    }

    @Override
    public void enter(ViewChangeEvent event) {
    }
}
