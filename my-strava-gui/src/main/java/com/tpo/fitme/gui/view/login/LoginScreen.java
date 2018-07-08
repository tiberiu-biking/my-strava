package com.tpo.fitme.gui.view.login;

import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthConstants;
import com.github.scribejava.core.oauth.OAuth20Service;
import com.tpo.fitme.domain.Athlete;
import com.tpo.fitme.gui.domain.UserSession;
import com.tpo.fitme.service.login.LoginService;
import com.tpo.fitme.service.sync.Synchronizer;
import com.vaadin.server.*;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Window;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.layouts.MVerticalLayout;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

@Slf4j
@Scope("prototype")
@SpringComponent
public class LoginScreen extends Window implements RequestHandler {

    @Autowired
    private OAuth20Service authService;

    @Autowired
    private UserSession userSession;

    @Autowired
    private Synchronizer synchronizer;

    @Autowired
    private LoginService loginService;

    private String redirectUrl;

    public LoginScreen() {
        super("Login");
    }

    @Override
    public void attach() {
        super.attach();
        VaadinSession.getCurrent().addRequestHandler(this);
        MVerticalLayout mVerticalLayout = buildContent();
        setContent(mVerticalLayout);
        redirectUrl = Page.getCurrent().getLocation().toString();
    }

    @Override
    public boolean handleRequest(VaadinSession vaadinSession, VaadinRequest vaadinRequest, VaadinResponse vaadinResponse) throws IOException {
        String oauthCode = vaadinRequest.getParameter(OAuthConstants.CODE);

        if (oauthCode != null) {
            try {

                OAuth2AccessToken oAuth2AccessToken = authService.getAccessToken(oauthCode);

                Athlete athlete = loginService.login(oAuth2AccessToken.getAccessToken());

                userSession.setUser(athlete);

                synchronizer.sync(userSession.getUser());

                close();
                VaadinSession.getCurrent().removeRequestHandler(this);
                ((VaadinServletResponse) vaadinResponse).getHttpServletResponse().sendRedirect(redirectUrl);
                return true;
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    private MVerticalLayout buildContent() {
        Button loginButton = buildOAuthButton();
        MVerticalLayout mVerticalLayout = new MVerticalLayout(loginButton).alignAll(Alignment.MIDDLE_CENTER).withFullHeight();
        setModal(true);
        setWidth("400px");
        setHeight("300px");
        addStyleName("login");
        return mVerticalLayout;
    }

    private Button buildOAuthButton() {
        String authorizationUrl = authService.getAuthorizationUrl();
        log.info("Authorization url: " + authorizationUrl);

        return new MButton()
                .withStyleName("primary-strava")
                .withIcon(new ThemeResource("img/connect-with-strava.png"))
                .withUndefinedSize()
                .withListener(
                        (Button.ClickListener) clickEvent ->
                                Page.getCurrent().setLocation(authorizationUrl));
    }
}
