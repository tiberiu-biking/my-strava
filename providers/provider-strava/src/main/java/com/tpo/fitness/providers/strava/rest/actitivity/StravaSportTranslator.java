package com.tpo.fitness.providers.strava.rest.actitivity;

import com.tpo.fitme.domain.Sport;
import com.tpo.fitme.domain.WorkoutType;
import lombok.experimental.UtilityClass;

import static com.tpo.fitme.domain.Sport.*;

/**
 * @author Tiberiu
 * @since 21.06.18
 */
@UtilityClass
public class StravaSportTranslator {

    public static Sport translate(String sport, Integer workoutType) {
        Sport result = Sport.valueOf(sport.toUpperCase());

        if (RIDE.equals(result)) {
            return translateRide(workoutType);
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
