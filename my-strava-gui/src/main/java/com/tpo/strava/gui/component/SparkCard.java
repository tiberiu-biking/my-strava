package com.tpo.strava.gui.component;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class SparkCard extends VerticalLayout {

    public SparkCard(final String name, String value) {
        setSizeUndefined();
        addStyleName("spark");
        setDefaultComponentAlignment(Alignment.TOP_CENTER);

        Label current = new Label(value);
        current.setSizeUndefined();
        current.addStyleName(ValoTheme.LABEL_HUGE);

        addComponent(current);

        Label title = new Label(name);
        title.setSizeUndefined();
        title.addStyleName(ValoTheme.LABEL_SMALL);
        title.addStyleName(ValoTheme.LABEL_LIGHT);

        addComponent(title);
    }

}
