package com.tpo.fitness.providers.strava.rest.athlete;

import com.tpo.fitness.domain.Athlete;
import com.tpo.fitness.providers.api.service.AthleteRestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import static com.tpo.fitness.providers.strava.rest.StravaRestConstants.ACCESS_TOKEN;
import static com.tpo.fitness.providers.strava.rest.StravaRestConstants.ATHLETE_URL;

/**
 * @author Tiberiu
 * @since 08.10.17
 */
@Service
public class StravaAthleteRestClient implements AthleteRestClient {

    private static final Logger logger = LoggerFactory.getLogger(StravaAthleteRestClient.class);

    private final RestTemplate restTemplate;

    public StravaAthleteRestClient() {
        restTemplate = new RestTemplate();
    }

    @Override
    public Athlete getAthlete(String authToken) {
        String uriString = UriComponentsBuilder
                .fromUriString(ATHLETE_URL)
                .queryParam(ACCESS_TOKEN, authToken)
                .toUriString();
        logger.debug("ActivityEntity details uri {}", uriString);
        StravaAthlete stravaAthlete = restTemplate.getForObject(uriString, StravaAthlete.class);
        Athlete translate = StravaAthleteTranslator.translate(stravaAthlete);
        translate.setAuthToken(authToken);
        return translate;
    }

}