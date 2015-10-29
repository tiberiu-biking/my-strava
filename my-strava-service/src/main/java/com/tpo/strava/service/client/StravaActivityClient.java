package com.tpo.strava.service.client;

import com.tpo.strava.service.domain.activity.Activity;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;

import java.util.List;

/**
 * Created by Tiberiu on 29/10/15.
 */
public interface StravaActivityClient {

    @GET("/athlete/activities/{activityId}")
    Call<Activity> getActivity(@Path("activityId") String activityId);

    @GET("/athlete/activities")
    Call<List<Activity>> getActivities();

}
