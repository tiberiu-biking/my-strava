package com.tpo.fitme.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tiberiu on 21/10/15.
 */
@Getter
@Setter
public class Athlete {

    private Long id;
    private String username;
    private String name;
    private String profileMediumPicture;
    private String profile;
    private String city;
    private String state;
    private String sex;
    private List<Gear> gears = new ArrayList<>();
    private String authToken;
}