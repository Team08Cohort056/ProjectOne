package com.project.oop.tasksmanagement.utils;

import java.util.List;

public class ValidationHelpers {

    private static final String INVALID_NUM_OF_ARGUMENTS = "Invalid number of arguments. Expected: %d; received: %d.";

    public static void validateStringLength(String value, int minLength, int maxLength, String errorMessege) {
        if (value.length() < minLength || value.length() > maxLength) {
            throw new IllegalArgumentException(errorMessege);
        }
    }

    public static void validateArgumentsCount(List<String> list, int expectedNumberOfParameters) {
        if (list.size() < expectedNumberOfParameters) {
            throw new IllegalArgumentException(
                    String.format(INVALID_NUM_OF_ARGUMENTS, expectedNumberOfParameters, list.size())
            );
        }
    }
    public static void validateIntRange(int value, int min, int max, String message) {
        if (value < min || value > max) {
            throw new IllegalArgumentException(message);
        }
    }
}
