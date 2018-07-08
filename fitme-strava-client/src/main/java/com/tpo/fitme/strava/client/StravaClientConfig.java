package com.tpo.fitme.strava.client;

import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.oauth.OAuth20Service;
import com.tpo.fitme.strava.client.oauth.OAuthProperties;
import com.tpo.fitme.strava.client.oauth.StravaOAuth2Api;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.web.client.RestTemplate;

/**
 * @author Tiberiu
 * @since 08.10.17
 */
@EnableRetry
@Configuration
@ComponentScan
public class StravaClientConfig {

    private static final String OAUTH_PUBLIC_SCOPE = "public";

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder
                .setConnectTimeout(3000)
                .setReadTimeout(3000)
                .build();
    }

    @Bean
    public OAuth20Service createService(OAuthProperties oauthProperties) {
        StravaOAuth2Api api = new StravaOAuth2Api();
        return new ServiceBuilder(oauthProperties.getClientId())
                .apiSecret(oauthProperties.getSecret())
                .callback(oauthProperties.getCallbackUrl())
                .scope(OAUTH_PUBLIC_SCOPE)
                .build(api);
    }

}
