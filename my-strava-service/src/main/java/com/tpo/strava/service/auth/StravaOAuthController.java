package com.tpo.strava.service.auth;

import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.Token;
import com.github.scribejava.core.model.Verifier;
import com.github.scribejava.core.oauth.OAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Tiberiu on 20/10/15.
 */
@RestController
public class StravaOAuthController {

    private static final Logger logger = LoggerFactory.getLogger(StravaOAuthController.class);
    private static final Token EMPTY_TOKEN = null;

    private OAuthService authService;

    private String clientSecret;
    private String clientId;

    @Value("${strava.oauth.token.url}")
    private String redirectUri;

    @Value("${strava.token.url}")
    private String authTokenUrl;

    public StravaOAuthController(String clientSecret, String clientId) {
        this.clientSecret = clientSecret;
        this.clientId = clientId;
    }

    @RequestMapping("/strava/oauth/authorize")
    public ModelAndView authorize(HttpServletRequest request, HttpServletResponse response) {
        authService = new ServiceBuilder()
                .provider(StravaOAuth2Api.class)
                .apiKey(clientId)
                .apiSecret(clientSecret)
                .callback(redirectUri)
                .build();

        String authorizationUrl = authService.getAuthorizationUrl(EMPTY_TOKEN);
        logger.info("Authorization url: " + authorizationUrl);

        return new ModelAndView(new RedirectView(authorizationUrl));
    }

    @RequestMapping("/strava/oauth/token")
    public ModelAndView exchangeToken(@RequestParam(value = "code") String authCode) {
        logger.info("Auth code: " + authCode);
        Verifier verifier = new Verifier(authCode);
        Token accessToken = authService.getAccessToken(EMPTY_TOKEN, verifier);
        logger.info("Access token:" + accessToken.getToken());
        return new ModelAndView(new RedirectView(authTokenUrl + accessToken.getToken()));
    }
}