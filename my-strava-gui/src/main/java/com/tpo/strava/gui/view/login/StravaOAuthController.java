package com.tpo.strava.gui.view.login;

import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.oauth.OAuth20Service;
import com.tpo.fitness.service.properties.AppProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 * Created by Tiberiu on 20/10/15.
 */
@Slf4j
@RestController
public class StravaOAuthController {

    private OAuth20Service authService;

    private String clientSecret;
    private String clientId;

    @Value("${strava.oauth.token.url}")
    private String redirectUri;

    @Value("${strava.token.url}")
    private String authTokenUrl;

    @Autowired
    private AppProperties appProperties;

    public StravaOAuthController(String clientSecret, String clientId) {
        this.clientSecret = clientSecret;
        this.clientId = clientId;
    }

    @RequestMapping("/strava/oauth/authorize")
    public ModelAndView authorize(HttpServletRequest request, HttpServletResponse response) {

        StravaOAuth2Api api = new StravaOAuth2Api();

        authService = new ServiceBuilder(clientId)
                .apiSecret(clientSecret)
                .callback(redirectUri)
                .debug()
                .scope("public")
                .build(api);

        String authorizationUrl = authService.getAuthorizationUrl();
        log.info("Authorization url: " + authorizationUrl);

        return new ModelAndView(new RedirectView(authorizationUrl));
    }

    @RequestMapping("/strava/oauth/token")
    public ModelAndView exchangeToken(@RequestParam(value = "code") String authCode) throws InterruptedException, ExecutionException, IOException {
        log.info("Auth code: " + authCode);
        OAuth2AccessToken accessToken = authService.getAccessToken(authCode);
        log.info("Access token:" + accessToken.getAccessToken());
        return new ModelAndView(new RedirectView(authTokenUrl + accessToken.getAccessToken()));
    }
}