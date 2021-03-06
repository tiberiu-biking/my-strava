package com.tpo.fitme.strava.client.oauth;

import com.github.scribejava.core.builder.api.ClientAuthenticationType;
import com.github.scribejava.core.builder.api.DefaultApi20;

/**
 * @author Tiberiu
 * @since 30/11/15.
 */
public class StravaOAuth2Api extends DefaultApi20 {

    @Override
    public String getAccessTokenEndpoint() {
        return "https://www.strava.com/oauth/token";
    }

    @Override
    protected String getAuthorizationBaseUrl() {
        return "https://www.strava.com/oauth/authorize?approval_prompt=auto";
    }

    @Override
    public ClientAuthenticationType getClientAuthenticationType() {
        return ClientAuthenticationType.REQUEST_BODY;
    }
}
