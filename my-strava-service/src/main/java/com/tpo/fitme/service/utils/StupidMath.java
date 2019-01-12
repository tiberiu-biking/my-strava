package com.tpo.fitme.service.utils;

/**
 * @author Tiberiu
 * @since 12.01.19
 */
public class StupidMath {

    private StupidMath() {
    }

    public static float divide(float number, float by) {
        float i = number / by;
        return (float) Math.round(i * 100000) / 100000;
    }

}
