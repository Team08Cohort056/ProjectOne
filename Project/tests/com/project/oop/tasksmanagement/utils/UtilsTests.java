package com.project.oop.tasksmanagement.utils;

import java.util.Arrays;
import java.util.List;

public class UtilsTests {
    public static String getString(int length) {
        return "x".repeat(length);
    }
    public static List<String> getList(int size) {
        return Arrays.asList(new String[size]);
    }
}
