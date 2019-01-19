package com.tpo.strava.persistence.service.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author Tiberiu
 * @since 2019-01-19
 */
public final class Calendar {

    public static LocalDateTime startOfTheYear(int year) {
        return LocalDateTime.of(year, 1, 1, 0, 0, 0);
    }

    public static LocalDateTime endOfTheYear(int year) {
        return LocalDateTime.of(year, 12, 31, 23, 59, 59);
    }

    public static int year() {
        return LocalDateTime.now().getYear();
    }

    public static int lastYear() {
        return LocalDateTime.now().minusYears(1).getYear();
    }

    private Calendar() {
    }

    public static int dayOfYear() {
        return LocalDate.now().getDayOfYear();
    }
}
