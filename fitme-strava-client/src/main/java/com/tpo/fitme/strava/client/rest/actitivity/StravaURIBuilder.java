package com.tpo.fitme.strava.client.rest.actitivity;

import com.tpo.fitme.strava.client.rest.constants.StravaRestConstants;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * @author Tiberiu
 * @since 08.10.17
 */
class StravaURIBuilder {

    static String buildAthleteActivitiesByPageUrl(String accessToken, int page) {
        return UriComponentsBuilder
                .fromUriString(StravaRestConstants.ATHLETE_ACTIVITIES_URL)
                .queryParam(StravaRestConstants.ACCESS_TOKEN, accessToken)
                .queryParam(StravaRestConstants.PAGE, page)
                .toUriString();
    }

    static String buildAthleteActivitiesAfterUrl(String accessToken, LocalDateTime after, int page) {
        return UriComponentsBuilder
                .fromUriString(StravaRestConstants.ATHLETE_ACTIVITIES_URL)
                .queryParam(StravaRestConstants.ACCESS_TOKEN, accessToken)
                .queryParam(StravaRestConstants.AFTER, after.atZone(ZoneOffset.systemDefault()).toInstant().getEpochSecond())
                .queryParam(StravaRestConstants.PAGE, page)
                .toUriString();
    }

    static String buildActivityDetailsURL(String accessToken, String externalId) {
        return UriComponentsBuilder
                .fromUriString(StravaRestConstants.ACTIVITIES_URL)
                .queryParam(StravaRestConstants.ACCESS_TOKEN, accessToken)
                .pathSegment(externalId)
                .toUriString();
    }

}
