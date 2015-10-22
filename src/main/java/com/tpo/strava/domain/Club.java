package com.tpo.strava.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Tiberiu on 21/10/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Club {

    private int id;
    private int resource_state;
    private String name;


    public Club(int id) {
        this.id = id;
    }

    public Club() {
    }

    @Override
    public String toString() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getResource_state() {
        return resource_state;
    }

    public void setResource_state(int resource_state) {
        this.resource_state = resource_state;
    }
}