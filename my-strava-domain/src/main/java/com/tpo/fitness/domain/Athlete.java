package com.tpo.fitness.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by Tiberiu on 21/10/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Athlete {

    private int id;
    private String firstName;
    private String lastName;
    private String profileMediumPicture;
    private String profile;
    private String city;
    private String state;
    private String sex;

    public Athlete(int id) {
        this.id = id;
    }

    public Athlete() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getProfileMediumPicture() {
        return profileMediumPicture;
    }

    public void setProfileMediumPicture(String profileMediumPicture) {
        this.profileMediumPicture = profileMediumPicture;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("lastName", lastName)
                .append("firstName", firstName)
                .append("profile", profile)
                .append("city", city)
                .append("sex", sex)
                .toString();
    }
}