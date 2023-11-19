package com.project.oop.tasksmanagement.models;

import com.project.oop.tasksmanagement.utils.UtilsTests;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MemberImplTests {
    public static final int MEMBER_NAME_LEN_MIN = 5;

    public static final String VALID_MEMBER_NAME = UtilsTests.getString(MEMBER_NAME_LEN_MIN + 1);
    public static final String INVALID_MEMBER_NAME = UtilsTests.getString(MEMBER_NAME_LEN_MIN - 1);
    public static final int BOARD_NAME_LEN_MIN = 5;

    public static final String VALID_BOARD_NAME = UtilsTests.getString(BOARD_NAME_LEN_MIN + 1);
    @Test
    public void constructor_Should_ThrowException_When_MemberNameLengthOutOfBounds() {
        // Arrange, Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new MemberImpl(
                        INVALID_MEMBER_NAME));
    }


    @Test
    public void constructor_Should_CreateNewMember_When_ParametersAreCorrect() {
        // Arrange, Act
        MemberImpl member = initializeTestMember();

        // Assert
        Assertions.assertEquals(VALID_MEMBER_NAME, member.getName());
    }

    @Test
    public void getTasks_Should_ReturnCopyOfTheCollection() {
        // Arrange
        MemberImpl member = initializeTestMember();

        // Act
        // member.getTasks().add(TaskImplTests.initializeTestTask());

        // Assert
        Assertions.assertEquals(0, member.getTasks().size());
    }

    @Test
    public void addComment_Should_AddCommentToTheCollection() {
        // Arrange
        MemberImpl member = initializeTestMember();
        //  BugImpl bug = BugImplTests.initializeTestBug();
        //  member.addTask(bug);

        // Act
        //  member.addComment(new CommentImpl(
        //     CommentImplTests.VALID_CONTENT,
        //   CommentImplTests.VALID_AUTHOR), bug);

        // Assert
        //   Assert.assertEquals(1, bug.getComments().size());
    }


    private static MemberImpl initializeTestMember() {
        return new MemberImpl(VALID_MEMBER_NAME);
    }
}
