package com.tpo.strava.gui.view;

import com.tpo.fitme.service.statistics.ActivitiesSummaryService;
import com.tpo.strava.gui.component.card.SparkCard;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Responsive;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

import static java.lang.String.valueOf;

@SpringView(name = DashboardView.VIEW_NAME)
public final class DashboardView extends Panel implements View {

    public static final String VIEW_NAME = "";

    private final VerticalLayout root;
    private final ActivitiesSummaryService activitiesSummaryService;
    private CssLayout dashboardPanels;

    @Autowired
    public DashboardView(ActivitiesSummaryService activitiesSummaryService) {
        this.activitiesSummaryService = activitiesSummaryService;
        addStyleName(ValoTheme.PANEL_BORDERLESS);
        setSizeFull();

        root = new VerticalLayout();
        root.setSizeFull();
        root.setMargin(true);
        root.addStyleName("dashboard-view");
        setContent(root);
        Responsive.makeResponsive(root);

        root.addComponent(buildSparkCards());

        Component content = buildContent();
        root.addComponent(content);
        root.setExpandRatio(content, 1);
    }

    private Component buildContent() {
        dashboardPanels = new CssLayout();
        dashboardPanels.addStyleName("dashboard-panels");
        Responsive.makeResponsive(dashboardPanels);
//        dashboardPanels.addComponent(createContentWrapper(new AchievementsCard(activities)));
        return dashboardPanels;
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

    private Component buildSparkCards() {
        CssLayout sparks = new CssLayout();
        sparks.addStyleName("sparks");
        sparks.setWidth("100%");
        Responsive.makeResponsive(sparks);

        SparkCard worldTripsSparkCard = new SparkCard("trips around the world", valueOf(activitiesSummaryService.getTripsAroundTheWorld()));
        sparks.addComponent(worldTripsSparkCard);

        SparkCard moonTripsSparkCard = new SparkCard("trips to the moon", valueOf(activitiesSummaryService.getTripsToTheMoon()));
        sparks.addComponent(moonTripsSparkCard);

        SparkCard burgersBurnedSparkCard = new SparkCard("burgers burned", valueOf(activitiesSummaryService.getBurgerBurned()));
        sparks.addComponent(burgersBurnedSparkCard);

        SparkCard biersBurnedSparkCard = new SparkCard("beers burned", valueOf(activitiesSummaryService.getBeersBurned()));
        sparks.addComponent(biersBurnedSparkCard);

        return sparks;
    }




    @Override
    public void enter(ViewChangeEvent event) {
    }
}
