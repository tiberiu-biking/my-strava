package com.tpo.fitme.strava.client.oauth;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * Created by Tiberiu on 28/11/15.
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "strava.oauth")
@Validated
public class OAuthProperties {

    @NotEmpty
    private String url;
    @NotEmpty
    private String clientId;
    @NotEmpty
    private String secret;
    @NotEmpty
    private String callbackUrl;
}
