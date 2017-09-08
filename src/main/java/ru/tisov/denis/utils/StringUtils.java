package ru.tisov.denis.utils;

/**
 * @author dinyat
 * 08/09/2017
 */
public class StringUtils {

    private StringUtils() {
        throw new IllegalStateException("This constructor shouldn't be called");
    }

    public static boolean isEmpty(String input) {
        return input == null || input.trim().isEmpty();
    }

}
