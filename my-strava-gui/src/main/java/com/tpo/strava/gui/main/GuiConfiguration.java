package com.tpo.strava.gui.main;

import com.tpo.strava.service.config.ServiceConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by Tiberiu on 25/10/15.
 */
@Configuration
@Import(ServiceConfiguration.class)
public class GuiConfiguration {

}
