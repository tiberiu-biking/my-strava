package com.tpo.strava.gui.view.main;

import com.tpo.fitme.gui.domain.UserSession;
import com.tpo.fitness.service.sync.Synchronizer;
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

    private final SpringViewProvider springViewProvider;
    private final UserSession userSession;
    private final Synchronizer synchronizer;

    @Autowired
    public MainScreen(SpringViewProvider springViewProvider, UserSession userSession, Synchronizer synchronizer) {
        this.springViewProvider = springViewProvider;
        this.userSession = userSession;
        this.synchronizer = synchronizer;
        addStyleName("mainview");
        setSizeFull();
    }

    public void refresh() {
        if (userSession.isLoggedIn()) {
            HorizontalLayout layout = new HorizontalLayout();
            layout.setSizeFull();
            setCompositionRoot(layout);
            CssLayout viewContainer = new CssLayout();
            viewContainer.setSizeFull();

            layout.addComponent(new DashboardMenu(userSession.getUser(), synchronizer));
            layout.addComponent(viewContainer);
            layout.setExpandRatio(viewContainer, 1f);

            Navigator navigator = new Navigator(UI.getCurrent(), viewContainer);
            navigator.addProvider(springViewProvider);
            navigator.setErrorView(ErrorView.class);
            navigator.navigateTo(navigator.getState());
        }
    }
}