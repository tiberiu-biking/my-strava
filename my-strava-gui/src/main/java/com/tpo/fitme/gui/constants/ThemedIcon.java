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
    SOCCER("img/soccer.png"),
    SKI("img/ski.png"),
    MTB("img/mtb.png"),
    RUN("img/run.png"),
    YOGA("img/yoga.png"),
    TRX("img/trx.png"),
    HIIT("img/hiit.png"),
    SYNC("img/sync.png"),
    STRAVA("img/strava-black.png");

    private final ThemeResource resource;

    ThemedIcon(String themedResourceId) {
        resource = new ThemeResource(themedResourceId);
    }
}
