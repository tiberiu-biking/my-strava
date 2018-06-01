package com.tpo.strava.gui.view;

import com.tpo.fitness.domain.ActivitiesSummary;
import com.tpo.fitness.service.activity.ActivitiesService;
import com.tpo.strava.gui.component.card.SparkCard;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Responsive;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@SpringView(name = DashboardView.VIEW_NAME)
public final class DashboardView extends Panel implements View {

    public static final String VIEW_NAME = "";

    public static final int ECUATOR_LENGTH_KM = 40008;
    public static final int DISTANCE_TO_THE_MOON_KM = 384400;
    private static final float CALORIES_PER_BURGER = 295;
    private static final float CALORIES_PER_BEER = 43;

    private final VerticalLayout root;
    private final List<ActivitiesSummary> activities;
    private CssLayout dashboardPanels;


    @Autowired
    public DashboardView(ActivitiesService activitiesService) {
        activities = activitiesService.getSummary();
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

        float totalKm = getTotalKm();
        SparkCard worldTripsSparkCard = new SparkCard("trips around the world", Float.toString(totalKm / ECUATOR_LENGTH_KM));
        sparks.addComponent(worldTripsSparkCard);

        SparkCard moonTripsSparkCard = new SparkCard("trips to the moon", Float.toString(totalKm / DISTANCE_TO_THE_MOON_KM));
        sparks.addComponent(moonTripsSparkCard);

        float totalCalories = getTotalCalories();

        SparkCard burgersBurnedSparkCard = new SparkCard("burgers burned", Float.toString(totalCalories / CALORIES_PER_BURGER));
        sparks.addComponent(burgersBurnedSparkCard);

        SparkCard biersBurnedSparkCard = new SparkCard("beers burned", Long.toString(Math.round(totalCalories / CALORIES_PER_BEER)));
        sparks.addComponent(biersBurnedSparkCard);

        return sparks;
    }

    private float getTotalKm() {
        float totalDistance = 0;
        for (ActivitiesSummary summary : activities) {
            totalDistance = totalDistance + summary.getDistance();
        }
        return totalDistance;
    }

    private long getTotalCalories() {
        long totalCalories = 0L;

        for (ActivitiesSummary summary : activities) {
            totalCalories = totalCalories + summary.getCalories();
        }
        return totalCalories;
    }

    @Override
    public void enter(ViewChangeEvent event) {
    }
}
