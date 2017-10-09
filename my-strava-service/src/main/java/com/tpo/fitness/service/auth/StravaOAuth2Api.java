package com.tpo.fitness.service.auth;

import com.github.scribejava.core.builder.api.DefaultApi20;
import com.github.scribejava.core.extractors.AccessTokenExtractor;
import com.github.scribejava.core.extractors.JsonTokenExtractor;
import com.github.scribejava.core.model.OAuthConfig;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.utils.OAuthEncoder;

/**
 * @author Tiberiu
 * @since 30/11/15.
 */
public class StravaOAuth2Api extends DefaultApi20 {

    @Override
    public Verb getAccessTokenVerb() {
        return Verb.POST;
    }

    @Override
    public AccessTokenExtractor getAccessTokenExtractor() {
        return new JsonTokenExtractor();
    }

    @Override
    public String getAccessTokenEndpoint() {
        return "https://www.strava.com/oauth/token";
    }

    @Override
    public String getAuthorizationUrl(OAuthConfig oAuthConfig) {
        final StringBuilder sb = new StringBuilder(String.format("https://www.strava.com/oauth/authorize?client_id=%s&response_type=code&redirect_uri=%s", oAuthConfig.getApiKey(), OAuthEncoder.encode(oAuthConfig.getCallback())));
        return sb.toString();
    }
}
