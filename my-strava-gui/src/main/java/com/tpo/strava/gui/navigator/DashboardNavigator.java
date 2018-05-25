package com.tpo.strava.gui.navigator;

import com.tpo.strava.gui.event.DashboardEvent.BrowserResizeEvent;
import com.tpo.strava.gui.event.DashboardEvent.CloseOpenWindowsEvent;
import com.tpo.strava.gui.event.DashboardEvent.PostViewChangeEvent;
import com.tpo.strava.gui.event.DashboardEventBus;
import com.tpo.strava.gui.view.DashboardView;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.UI;

public class DashboardNavigator extends Navigator {

    public DashboardNavigator(final ComponentContainer container, SpringViewProvider springViewProvider) {
        super(UI.getCurrent(), container);
        initViewChangeListener();
        addProvider(springViewProvider);
        setErrorView(DashboardView.class);
    }

    private void initViewChangeListener() {
        addViewChangeListener(new ViewChangeListener() {

            @Override
            public boolean beforeViewChange(final ViewChangeEvent event) {
                return true;
            }

            @Override
            public void afterViewChange(final ViewChangeEvent event) {
                DashboardViewType view = DashboardViewType.getByViewName(event.getViewName());
                DashboardEventBus.post(new PostViewChangeEvent(view));
                DashboardEventBus.post(new BrowserResizeEvent());
                DashboardEventBus.post(new CloseOpenWindowsEvent());
            }
        });
    }
}
