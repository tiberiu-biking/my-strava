package com.tpo.fitme.gui.constants;

import com.vaadin.server.ThemeResource;
import lombok.Getter;

/**
 * @author Tiberiu
 * @since 19.06.18
 */
@Getter
public enum ThemedIcon {

    ROAD("img/road.png"),
    HIKE("img/hike.png"),
    SKI("img/ski.png"),
    MTB("img/mtb.png"),
    STRAVA("img/strava-black.png");

    private final ThemeResource resource;

    ThemedIcon(String themedResourceId) {
        resource = new ThemeResource(themedResourceId);
    }
}
