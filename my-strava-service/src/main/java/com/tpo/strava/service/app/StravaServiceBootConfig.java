package com.tpo.strava.service.app;

import com.tpo.strava.service.config.ControllerConfig;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by Tiberiu on 20/10/15.
 */
@Configuration
@EnableAutoConfiguration
@Import(ControllerConfig.class)
public class StravaServiceBootConfig {
}
