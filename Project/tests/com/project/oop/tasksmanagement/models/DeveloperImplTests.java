package com.project.oop.tasksmanagement.models;

import com.project.oop.tasksmanagement.models.tasks.BugImpl;
import com.project.oop.tasksmanagement.utils.UtilsTests;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DeveloperImplTests {
    public static final int DEVELOPER_NAME_LEN_MIN = 5;

    public static final String VALID_DEVELOPER_NAME = UtilsTests.getString(DEVELOPER_NAME_LEN_MIN + 1);
    public static final String INVALID_DEVELOPER_NAME = UtilsTests.getString(DEVELOPER_NAME_LEN_MIN - 1);
    public static final int BOARD_NAME_LEN_MIN = 5;

    public static final String VALID_BOARD_NAME = UtilsTests.getString(BOARD_NAME_LEN_MIN + 1);
    @Test
    public void constructor_Should_ThrowException_When_DeveloperNameLengthOutOfBounds() {
        // Arrange, Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new DeveloperImpl(
                        INVALID_DEVELOPER_NAME));
    }


    @Test
    public void constructor_Should_CreateNewDeveloper_When_ParametersAreCorrect() {
        // Arrange, Act
        DeveloperImpl developer = initializeTestDeveloper();

        // Assert
        Assertions.assertEquals(VALID_DEVELOPER_NAME, developer.getName());
    }

    @Test
    public void getTasks_Should_ReturnCopyOfTheCollection() {
        // Arrange
        DeveloperImpl developer = initializeTestDeveloper();

        // Act
        // developer.getTasks().add(TaskImplTests.initializeTestTask());

        // Assert
        Assertions.assertEquals(0, developer.getTasks().size());
    }

    @Test
    public void addComment_Should_AddCommentToTheCollection() {
        // Arrange
        DeveloperImpl developer = initializeTestDeveloper();
        //  BugImpl bug = BugImplTests.initializeTestBug();
        //  developer.addTask(bug);

        // Act
        //  developer.addComment(new CommentImpl(
        //     CommentImplTests.VALID_CONTENT,
        //   CommentImplTests.VALID_AUTHOR), bug);

        // Assert
        //   Assert.assertEquals(1, bug.getComments().size());
    }


    private static DeveloperImpl initializeTestDeveloper() {
        return new DeveloperImpl(VALID_DEVELOPER_NAME);
    }
}
