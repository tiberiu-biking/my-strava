package com.tpo.strava.service.strava.gui.user;

import com.vaadin.server.ExternalResource;
import com.vaadin.server.FileResource;
import com.vaadin.ui.Link;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

/**
 * Created by Tiberiu on 29/10/15.
 */
public class UserUI extends Panel {

    public UserUI() {
        VerticalLayout content = new VerticalLayout();
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
