package com.tpo.strava.service.strava.gui.user;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.FileResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Link;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

/**
 * Created by Tiberiu on 29/10/15.
 */
public class ConnectView extends HorizontalLayout implements View {

    public ConnectView() {
        ClassPathResource sourceFile = new ClassPathResource("connect-with-strava.png");
        FileResource icon = null;
        try {
            icon = new FileResource(sourceFile.getFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Link connectLink = new Link("", new ExternalResource("http://my-strava.eu-gb.mybluemix.net/strava/oauth/authorize"));
        connectLink.setIcon(icon);
        addComponent(connectLink);
        setComponentAlignment(connectLink, Alignment.MIDDLE_RIGHT);
        connectLink.setWidth(null);
        connectLink.setWidthUndefined();
        setExpandRatio(connectLink, 1.0f);
        setWidth("100%");
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
    }
}
