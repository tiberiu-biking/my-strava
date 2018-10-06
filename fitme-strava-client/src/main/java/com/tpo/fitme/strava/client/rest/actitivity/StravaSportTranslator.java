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

    static Sport translate(String sport, Integer workoutType, String name) {
        Sport result = Sport.valueOf(sport.toUpperCase());

        if (RIDE.equals(result)) {
            return translateRide(workoutType);
        } else if (WORKOUT.equals(result)) {
            return translateWorkout(result, name);
        } else {
            return result;
        }
    }

    private static Sport translateWorkout(Sport result, String name) {
        if (name != null &&
                (name.contains("Soccer") || name.contains("soccer") ||
                        name.contains("football") || name.contains("Football"))) {
            return SOCCER;
        } else {
            return result;
        }
    }

    private static Sport translateRide(Integer workoutType) {
        if (workoutType != null && WorkoutType.RACE.getValue() == workoutType) {
            return ROAD;
        } else {
            return MTB;
        }
    }
}
