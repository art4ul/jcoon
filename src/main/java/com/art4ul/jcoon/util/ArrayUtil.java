package com.art4ul.jcoon.util;

/**
 * Created by Artsem Semianenka  http://art4ul.com on 10/24/14.
 */
public class ArrayUtil {

    public static <T> T getFirstValue(T[] array) {
        if (array == null || array.length == 0) {
            return null;
        }
        return array[0];

    }

    public static <T> T getFirstOrDefault(T[] array, T defaultValue) {
        if (array == null || array.length == 0) {
            return defaultValue;
        }
        return array[0];
    }

}
