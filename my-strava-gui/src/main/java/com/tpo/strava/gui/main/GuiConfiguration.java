package com.tpo.strava.gui.main;

import com.tpo.strava.gui.view.DashboardView;
import com.tpo.strava.web.config.WebConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by Tiberiu on 25/10/15.
 */
@Configuration
@Import(WebConfiguration.class)
@ComponentScan(basePackageClasses = DashboardView.class)
public class GuiConfiguration {

}
