package com.tpo.fitme.strava.client.rest.actitivity;

import com.tpo.fitme.domain.Sport;

/**
 * @author Tiberiu
 * @since 06.01.19
 */
public final class Calories {

    private Calories() {
    }

    public static Long calculate(Long calories, Long movingTime, Sport sport) {
        if (calories != null && calories > 0) {
            return calories;
        }

        return movingTime * sport.getCaloriesPerMinute();
    }
}
