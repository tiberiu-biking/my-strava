package com.tpo.fitme.strava.client.rest.athlete;

import com.tpo.fitme.domain.Athlete;
import com.tpo.fitme.strava.client.rest.AthleteRestClient;
import com.tpo.fitme.strava.client.rest.constants.StravaRestConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * @author Tiberiu
 * @since 08.10.17
 */
@Slf4j
@Service
public class StravaAthleteRestClient implements AthleteRestClient {

    private final RestTemplate restTemplate;
    private final StravaAthleteMapper stravaAthleteMapper;

    @Autowired
    public StravaAthleteRestClient(RestTemplate restTemplate, StravaAthleteMapper stravaAthleteMapper) {
        this.restTemplate = restTemplate;
        this.stravaAthleteMapper = stravaAthleteMapper;
    }

    @Override
    @Retryable(maxAttempts = 10)
    public Athlete getAthlete(String authToken) {
        String uriString = UriComponentsBuilder
                .fromUriString(StravaRestConstants.ATHLETE_URL)
                .queryParam(StravaRestConstants.ACCESS_TOKEN, authToken)
                .toUriString();
        log.debug("Activity details uri {}", uriString);
        StravaAthlete stravaAthlete = restTemplate.getForObject(uriString, StravaAthlete.class);
        Athlete translate = stravaAthleteMapper.map(stravaAthlete);
        translate.setAuthToken(authToken);
        return translate;
    }

}