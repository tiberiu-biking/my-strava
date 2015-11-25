package com.tpo.strava.service.controller;

import com.tpo.strava.service.domain.Athlete;
import com.tpo.strava.service.domain.TokenResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Tiberiu on 20/10/15.
 */
@RestController
public class StravaOAuthController {

    private static final String STRAVA_AUTH_URL = "https://www.strava.com/oauth/authorize";
    private static final String STRAVA_TOKEN_URL = "https://www.strava.com/oauth/token";
    private static final String RESPONSE_TYPE_CODE = "code";

    private static final Logger logger = LoggerFactory.getLogger(StravaOAuthController.class);

    private String clientSecret;
    private String clientId;

    public StravaOAuthController(String clientSecret, String clientId) {
        this.clientSecret = clientSecret;
        this.clientId = clientId;
    }

    @RequestMapping("/strava/oauth/authorize")
    public ModelAndView authorize(HttpServletRequest request, HttpServletResponse response) {
        String uri = UriComponentsBuilder
                .fromHttpUrl(STRAVA_AUTH_URL)
                .queryParam("client_id", clientId)
                .queryParam("redirect_uri", "http://my-strava.eu-gb.mybluemix.net/strava/oauth/token")
                .queryParam("response_type", RESPONSE_TYPE_CODE)
                .toUriString();

        logger.info("Authorize request: " + uri);
        return new ModelAndView(new RedirectView(uri));
    }

    @RequestMapping("/strava/oauth/token")
    public ModelAndView exchangeToken(@RequestParam(value = "code") String authCode) {
        logger.info("Received auth code: " + authCode);

        Map<String, String> params = new HashMap<>();
        params.put("client_id", clientId);
        params.put("client_secret", clientSecret);
        params.put("code", authCode);

        RestTemplate restTemplate = new RestTemplate();
        TokenResponse tokenResponse = restTemplate.postForObject(STRAVA_TOKEN_URL, params, TokenResponse.class);
        Athlete athlete = tokenResponse.getAthlete();
        logger.info("Access token:" + tokenResponse.getAccessToken());
        return new ModelAndView(new RedirectView("http://my-strava.eu-gb.mybluemix.net/?authToken=" + tokenResponse.getAccessToken()));
    }
}