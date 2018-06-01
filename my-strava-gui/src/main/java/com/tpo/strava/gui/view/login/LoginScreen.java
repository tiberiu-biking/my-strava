package com.tpo.strava.gui.view.login;

import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.oauth.OAuth20Service;
import com.tpo.fitness.service.properties.AppProperties;
import com.vaadin.server.*;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Link;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.vaadin.viritin.layouts.MVerticalLayout;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

@Scope("prototype")
@SpringComponent
@Slf4j
public class LoginScreen extends Window implements RequestHandler {

    private final String redirectUrl;
    private OAuth20Service authService;

    @Autowired
    private UserSession userSession;

    @Value("${strava.oauth.token.url}")
    private String redirectUri;

    @Value("${strava.token.url}")
    private String authTokenUrl;

    @Autowired
    private AppProperties appProperties;

    public LoginScreen() {
        super("Login");
        redirectUrl = "http://localhost:8080";//Page.getCurrent().getLocation().toString();
    }

    private String getCallbackUrl() {
        String callBackUrl = Page.getCurrent().getLocation().toString();
        if (callBackUrl.contains("#")) {
            callBackUrl = callBackUrl.substring(0, callBackUrl.indexOf("#"));
        }
        return callBackUrl;
    }

    private OAuth20Service createService() {
        StravaOAuth2Api api = new StravaOAuth2Api();

        return new ServiceBuilder(appProperties.getOatuhClientId())
                .apiSecret(appProperties.getOauthClientSecret())
                .callback(getCallbackUrl())
                .debug()
                .scope("public")
                .build(api);
    }

    @Override
    public void attach() {
        super.attach();
        authService = createService();
        String authorizationUrl = authService.getAuthorizationUrl();
        log.info("Authorization url: " + authorizationUrl);

        Link login2 = new Link("Login with Strava", new ExternalResource(authorizationUrl));
        login2.addStyleName(ValoTheme.LINK_LARGE);

        VaadinSession.getCurrent().addRequestHandler(this);

        setContent(new MVerticalLayout(login2).alignAll(
                Alignment.MIDDLE_CENTER).withFullHeight());
        setModal(true);
        setWidth("300px");
        setHeight("200px");

    }

    @Override
    public boolean handleRequest(VaadinSession vaadinSession, VaadinRequest vaadinRequest, VaadinResponse vaadinResponse) throws IOException {
        if (vaadinRequest.getParameter("code") != null) {
            String code = vaadinRequest.getParameter("code");
            try {
                OAuth2AccessToken token = authService.getAccessToken(code);
                userSession.login(token.getAccessToken());
                close();
                VaadinSession.getCurrent().removeRequestHandler(this);

                userSession.login(token.getAccessToken());

                ((VaadinServletResponse) vaadinResponse).getHttpServletResponse().
                        sendRedirect(redirectUrl);
                return true;
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        return false;
    }


    public void authDenied(String reason) {
        Notification.show("authDenied:" + reason,
                Notification.Type.ERROR_MESSAGE);
    }

}
