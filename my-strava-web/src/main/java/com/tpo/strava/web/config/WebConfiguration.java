package com.tpo.strava.web.config;

import com.tpo.strava.service.config.ServicesConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author Tiberiu
 * @since 03.10.17
 */
@Configuration
@Import({ServicesConfiguration.class, ControllerConfig.class})
public class WebConfiguration {
}
