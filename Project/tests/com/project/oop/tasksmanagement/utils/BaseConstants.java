package com.project.oop.tasksmanagement.utils;

public class BaseConstants {
    public static final int MIN_NAME_LENGTH = 5;
    public static final int TITLE_MIN_LENGTH = 10;
    public static final int DESCRIPTION_MIN_LENGTH = 10;
    public static final int STORY_MIN_ID = 1;

    public static final String VALID_NAME = UtilsTests.getString(MIN_NAME_LENGTH + 1);
    public static final String INVALID_NAME = UtilsTests.getString(MIN_NAME_LENGTH - 1);

    public static final String VALID_TITLE = UtilsTests.getString(TITLE_MIN_LENGTH + 1);
    public static final String INVALID_TITLE = UtilsTests.getString(TITLE_MIN_LENGTH - 1);

    public static final String VALID_DESCRIPTION = UtilsTests.getString(DESCRIPTION_MIN_LENGTH + 1);
    public static final String INVALID_DESCRIPTION = UtilsTests.getString(DESCRIPTION_MIN_LENGTH - 1);
    public static final int FEEDBACK_MIN_ID = 1;



}
