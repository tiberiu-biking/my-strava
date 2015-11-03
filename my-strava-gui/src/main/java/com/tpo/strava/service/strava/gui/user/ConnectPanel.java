package com.tpo.strava.service.strava.gui.user;

import com.vaadin.server.ExternalResource;
import com.vaadin.server.FileResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Link;
import com.vaadin.ui.Panel;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

/**
 * Created by Tiberiu on 29/10/15.
 */
public class ConnectPanel extends Panel {

    public ConnectPanel() {
        HorizontalLayout content = new HorizontalLayout();
        content.setDefaultComponentAlignment(Alignment.BOTTOM_RIGHT);
        ClassPathResource sourceFile = new ClassPathResource("connect-with-strava.png");
        FileResource icon = null;
        try {
            icon = new FileResource(sourceFile.getFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Link connectLink = new Link("", new ExternalResource("http://localhost:8080/strava/oauth/authorize"));
        connectLink.setIcon(icon);
        content.addComponent(connectLink);
        setContent(content);
    }
}
