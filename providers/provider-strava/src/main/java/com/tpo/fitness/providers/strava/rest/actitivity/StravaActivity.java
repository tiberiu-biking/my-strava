package com.tpo.fitness.providers.strava.rest.actitivity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tpo.fitness.domain.Athlete;

public class StravaActivity {

    private String id;
    private int resource_state;
    private String external_id;
    private int upload_id;
    private Athlete athlete;
    private String name;
    private Float distance;
    @JsonProperty("moving_time")
    private Integer movingTime;
    @JsonProperty("elapsed_time")
    private Integer elapsedTime;
    @JsonProperty("total_elevation_gain")
    private Float total_elevation_gain;
    private String type;
    private String start_date;
    private String start_date_local;
    private String timezone;
    private String[] start_latlng;
    private String[] end_latlng;
    private String location_city;
    private String location_state;
    private int achievement_count;
    private int kudos_count;
    private int comment_count;
    private int athlete_count;
    private int photo_count;
    //    private Polyline map;
    private boolean trainer;
    private boolean commute;
    private boolean manual;
    @JsonProperty("private")
    private boolean isPrivate;
    private boolean flagged;
    private String gear_id;
    private Float average_speed;
    private Float max_speed;
    private Float average_cadence;
    private int average_temp;
    private Float average_watts;
    private Float kilojoules;
    private Float average_heartrate;
    private Float max_heartrate;
    private Float calories;
    private int truncated;
    private boolean has_kudoed;
//    private List<SegmentEf  fort> segment_efforts;
//    private List<SplitsMetric> splits_metric;
//    private List<SplitsStandard> splits_standard;
//    private List<SegmentEffort> best_efforts;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getResource_state() {
        return resource_state;
    }

    public void setResource_state(int resource_state) {
        this.resource_state = resource_state;
    }

    public String getExternal_id() {
        return external_id;
    }

    public void setExternal_id(String external_id) {
        this.external_id = external_id;
    }

    public int getUpload_id() {
        return upload_id;
    }

    public void setUpload_id(int upload_id) {
        this.upload_id = upload_id;
    }

    public Athlete getAthlete() {
        return athlete;
    }

    public void setAthlete(Athlete athlete) {
        this.athlete = athlete;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getDistance() {
        return distance;
    }

    public void setDistance(Float distance) {
        this.distance = distance;
    }

    public Integer getMovingTime() {
        return movingTime;
    }

    public void setMovingTime(Integer movingTime) {
        this.movingTime = movingTime;
    }

    public Integer getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(Integer elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public Float getTotal_elevation_gain() {
        return total_elevation_gain;
    }

    public void setTotal_elevation_gain(Float total_elevation_gain) {
        this.total_elevation_gain = total_elevation_gain;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getStart_date_local() {
        return start_date_local;
    }

    public void setStart_date_local(String start_date_local) {
        this.start_date_local = start_date_local;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String[] getStart_latlng() {
        return start_latlng;
    }

    public void setStart_latlng(String[] start_latlng) {
        this.start_latlng = start_latlng;
    }

    public String[] getEnd_latlng() {
        return end_latlng;
    }

    public void setEnd_latlng(String[] end_latlng) {
        this.end_latlng = end_latlng;
    }

    public String getLocation_city() {
        return location_city;
    }

    public void setLocation_city(String location_city) {
        this.location_city = location_city;
    }

    public String getLocation_state() {
        return location_state;
    }

    public void setLocation_state(String location_state) {
        this.location_state = location_state;
    }

    public int getAchievement_count() {
        return achievement_count;
    }

    public void setAchievement_count(int achievement_count) {
        this.achievement_count = achievement_count;
    }

    public int getKudos_count() {
        return kudos_count;
    }

    public void setKudos_count(int kudos_count) {
        this.kudos_count = kudos_count;
    }

    public int getComment_count() {
        return comment_count;
    }

    public void setComment_count(int comment_count) {
        this.comment_count = comment_count;
    }

    public int getAthlete_count() {
        return athlete_count;
    }

    public void setAthlete_count(int athlete_count) {
        this.athlete_count = athlete_count;
    }

    public int getPhoto_count() {
        return photo_count;
    }

    public void setPhoto_count(int photo_count) {
        this.photo_count = photo_count;
    }

    public boolean isTrainer() {
        return trainer;
    }

    public void setTrainer(boolean trainer) {
        this.trainer = trainer;
    }

    public boolean isCommute() {
        return commute;
    }

    public void setCommute(boolean commute) {
        this.commute = commute;
    }

    public boolean isManual() {
        return manual;
    }

    public void setManual(boolean manual) {
        this.manual = manual;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public boolean isFlagged() {
        return flagged;
    }

    public void setFlagged(boolean flagged) {
        this.flagged = flagged;
    }

    public String getGear_id() {
        return gear_id;
    }

    public void setGear_id(String gear_id) {
        this.gear_id = gear_id;
    }

    public Float getAverage_speed() {
        return average_speed;
    }

    public void setAverage_speed(Float average_speed) {
        this.average_speed = average_speed;
    }

    public Float getMax_speed() {
        return max_speed;
    }

    public void setMax_speed(Float max_speed) {
        this.max_speed = max_speed;
    }

    public Float getAverage_cadence() {
        return average_cadence;
    }

    public void setAverage_cadence(Float average_cadence) {
        this.average_cadence = average_cadence;
    }

    public int getAverage_temp() {
        return average_temp;
    }

    public void setAverage_temp(int average_temp) {
        this.average_temp = average_temp;
    }

    public Float getAverage_watts() {
        return average_watts;
    }

    public void setAverage_watts(Float average_watts) {
        this.average_watts = average_watts;
    }

    public Float getKilojoules() {
        return kilojoules;
    }

    public void setKilojoules(Float kilojoules) {
        this.kilojoules = kilojoules;
    }

    public Float getAverage_heartrate() {
        return average_heartrate;
    }

    public void setAverage_heartrate(Float average_heartrate) {
        this.average_heartrate = average_heartrate;
    }

    public Float getMax_heartrate() {
        return max_heartrate;
    }

    public void setMax_heartrate(Float max_heartrate) {
        this.max_heartrate = max_heartrate;
    }

    public Float getCalories() {
        return calories;
    }

    public void setCalories(Float calories) {
        this.calories = calories;
    }

    public int getTruncated() {
        return truncated;
    }

    public void setTruncated(int truncated) {
        this.truncated = truncated;
    }

    public boolean isHas_kudoed() {
        return has_kudoed;
    }

    public void setHas_kudoed(boolean has_kudoed) {
        this.has_kudoed = has_kudoed;
    }
}