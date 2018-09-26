package com.tpo.fitme.strava.client.rest;


import com.tpo.fitme.domain.Athlete;
import com.tpo.fitme.domain.Gear;

import java.util.List;

public interface GearRestClient {

    List<Gear> findAll(Athlete athlete);
}
