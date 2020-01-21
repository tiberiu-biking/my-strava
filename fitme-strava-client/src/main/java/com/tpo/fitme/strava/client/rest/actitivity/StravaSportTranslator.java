package com.tpo.fitme.strava.client.rest.actitivity;

import com.tpo.fitme.domain.Sport;
import com.tpo.fitme.domain.WorkoutType;
import lombok.experimental.UtilityClass;

import static com.tpo.fitme.domain.Sport.*;

/**
 * @author Tiberiu
 * @since 21.06.18
 */
@UtilityClass
class StravaSportTranslator {

    static Sport translate(String sport, Integer workoutType, String activityName) {
        Sport result = Sport.valueOf(sport.toUpperCase());

        if (RIDE.equals(result)) {
            return translateRide(workoutType);
        } else if (WORKOUT.equals(result)) {
            return translateWorkout(result, activityName);
        } else {
            return result;
        }
    }

    private static Sport translateWorkout(Sport sport, String activityName) {
        Sport result = sport;
        if (activityName != null) {
            activityName = activityName.toUpperCase();
            if ((activityName.contains("SOCCER") || activityName.contains("FOOTBALL"))) {
                result = SOCCER;
            } else if (activityName.contains("TRX")) {
                result = TRX;
            } else if (activityName.contains("TT") || activityName.contains("Table Tennis") || activityName.contains("Tischtennis")) {
                result = TABLETENNIS;
            } else if ((activityName.contains("HIIT"))) {
                result = HIIT;
            }
        }
        return result;
    }

    private static Sport translateRide(Integer workoutType) {
        if (workoutType != null && WorkoutType.RACE.getValue() == workoutType) {
            return ROAD;
        } else {
            return MTB;
        }
    }
}
