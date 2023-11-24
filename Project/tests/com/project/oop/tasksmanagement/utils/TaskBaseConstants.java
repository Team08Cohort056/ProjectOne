package com.project.oop.tasksmanagement.utils;

public class TaskBaseConstants {

    private static final int STORY_TITLE_MIN_LENGTH = 10;
    private static final int STORY_DESCRIPTION_MIN_LENGTH = 10;
    public static final int STORY_MIN_ID = 1;
    public static final String VALID_TITLE = UtilsTests.getString(STORY_TITLE_MIN_LENGTH + 1);

    public static final String INVALID_TITLE = UtilsTests.getString(STORY_TITLE_MIN_LENGTH - 1);
    public static final String VALID_DESCRIPTION = UtilsTests.getString(STORY_DESCRIPTION_MIN_LENGTH + 1);

    public static final String INVALID_DESCRIPTION = UtilsTests.getString(STORY_DESCRIPTION_MIN_LENGTH - 1);
    public static final int FEEDBACK_MIN_ID = 1;



}
