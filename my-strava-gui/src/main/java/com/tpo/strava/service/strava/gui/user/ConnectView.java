package com.tpo.strava.service.strava.gui.user;

import com.tpo.strava.service.athlete.AthleteService;
import com.tpo.strava.service.domain.Athlete;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.*;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Link;

import java.io.IOException;

/**
 * Created by Tiberiu on 29/10/15.
 */
public class ConnectView extends HorizontalLayout implements View, RequestHandler {

    private final String homeUrl;

    private AthleteService athleteService;

    public ConnectView(String authUrl, AthleteService athleteService) {
        this.athleteService = athleteService;
        homeUrl = Page.getCurrent().getLocation().toString();
        Link connectLink = new Link("", new ExternalResource(authUrl));
        connectLink.setIcon(new ThemeResource("img/connect-with-strava.png"));
        addComponent(connectLink);
        setComponentAlignment(connectLink, Alignment.MIDDLE_RIGHT);
        connectLink.setWidth(null);
        connectLink.setWidthUndefined();
        setExpandRatio(connectLink, 1.0f);
        setWidth("100%");
        VaadinSession.getCurrent().addRequestHandler(this);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
    }

    @Override
    public boolean handleRequest(VaadinSession session, VaadinRequest request, VaadinResponse response) throws IOException {
        String authToken = request.getParameter("authToken");

        Athlete athlete = athleteService.getAthlete(authToken);
        athlete.setToken(authToken);
        session.setAttribute(Athlete.class.getName(), athlete);
        VaadinSession.getCurrent().removeRequestHandler(this);
        ((VaadinServletResponse) response).getHttpServletResponse().sendRedirect(homeUrl);

        return true;
    }
}
