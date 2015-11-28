package com.tpo.strava.service.properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

/**
 * Created by Tiberiu on 28/11/15.
 */
public class AppProperties {

    private static final String STRAVA_OAUTH_URL_PROPERTY = "strava.oauth.url";
    private static final String STRAVA_OAUTH_CLIENT_SECRET_PROPERTY = "strava.oauth.client.secret";
    private static final String STRAVA_OAUTH_CLIENT_ID_PROPERTY = "strava.oauth.client.id";

    private final String oauthUrl;
    private final String oatuhClientSecret;
    private final String oatuhClientId;

    @Autowired
    public AppProperties(Environment environment) {
        this.oauthUrl = environment.getRequiredProperty(STRAVA_OAUTH_URL_PROPERTY);
        this.oatuhClientSecret = environment.getRequiredProperty(STRAVA_OAUTH_CLIENT_SECRET_PROPERTY);
        this.oatuhClientId = environment.getRequiredProperty(STRAVA_OAUTH_CLIENT_ID_PROPERTY);
    }

    public String getOauthUrl() {
        return oauthUrl;
    }

    public String getOauthClientSecret() {
        return oatuhClientSecret;
    }

    public String getOatuhClientId() {
        return oatuhClientId;
    }
}
