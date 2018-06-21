package com.tpo.fitme.gui.navigator;

import com.tpo.fitme.gui.view.dashboard.DashboardView;
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
            }
        });
    }
}
