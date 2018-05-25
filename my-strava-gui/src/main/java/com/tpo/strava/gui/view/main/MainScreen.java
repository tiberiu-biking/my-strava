package com.tpo.strava.gui.view.main;

import com.tpo.fitness.service.athlete.activity.AthleteService;
import com.tpo.strava.gui.view.dashboard.DashboardMenu;
import com.vaadin.navigator.Navigator;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import org.springframework.beans.factory.annotation.Autowired;

@UIScope
@SpringComponent
public class MainScreen extends CustomComponent {

    @Autowired
    public MainScreen(SpringViewProvider springViewProvider, AthleteService athleteService) {
        addStyleName("mainview");

        HorizontalLayout layout = new HorizontalLayout();
        layout.setSizeFull();
        setCompositionRoot(layout);
        setSizeFull();

        layout.addComponent(new DashboardMenu(athleteService.getAthlete()));

        CssLayout viewContainer = new CssLayout();
        viewContainer.setSizeFull();
        layout.addComponent(viewContainer);
        layout.setExpandRatio(viewContainer, 1f);

        Navigator navigator = new Navigator(UI.getCurrent(), viewContainer);
        navigator.addProvider(springViewProvider);
        navigator.setErrorView(ErrorView.class);
        navigator.navigateTo(navigator.getState());

    }
}
