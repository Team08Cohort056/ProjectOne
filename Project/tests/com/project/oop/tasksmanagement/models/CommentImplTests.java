package com.project.oop.tasksmanagement.models;

import com.project.oop.tasksmanagement.models.contracts.Comment;
import com.project.oop.tasksmanagement.utils.UtilsTests;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CommentImplTests {
    public static final int MEMBER_NAME_MIN_LEN = 5;
    public static final int COMMENT_MIN_LEN = 3;

    public static final String VALID_CONTENT = UtilsTests.getString(COMMENT_MIN_LEN + 1);
    public static final String VALID_AUTHOR = UtilsTests.getString(MEMBER_NAME_MIN_LEN + 1);
    public static final String INVALID_CONTENT = UtilsTests.getString(COMMENT_MIN_LEN - 1);

    @Test
    public void commentImpl_Should_ImplementCommentInterface() {
        // Arrange, Act
        CommentImpl comment = initializeTestComment();
        // Assert
        Assertions.assertTrue(comment instanceof Comment);
    }

    @Test
    public void constructor_Should_ThrowException_When_TaskIdLengthOutOfBounds() {
        // Arrange, Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> new CommentImpl(
                INVALID_CONTENT,
                VALID_AUTHOR
        ));
    }

    @Test
    public void constructor_Should_CreateNewComment_When_ParametersAreCorrect() {
        // Arrange, Act
        CommentImpl comment = initializeTestComment();

        // Assert
        Assertions.assertEquals(VALID_CONTENT, comment.getContent());
    }

    public static CommentImpl initializeTestComment() {
        return new CommentImpl(
                VALID_CONTENT,
                VALID_AUTHOR);
    }
}
