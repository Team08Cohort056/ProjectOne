package com.project.oop.tasksmanagement.models.tasks;

import com.project.oop.tasksmanagement.models.contracts.Feedback;
import com.project.oop.tasksmanagement.models.contracts.Task;
import com.project.oop.tasksmanagement.utils.BaseConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FeedbackImplTests {
    FeedbackImpl feedback;
    @BeforeEach
    public void before() {
        feedback = initializeTestFeedback();
    }

    @Test
    public void FeedbackImpl_Should_ImplementFeedbackInterface() {
        Assertions.assertTrue(feedback instanceof Feedback);
    }

    @Test
    public void FeedbackImpl_Should_ImplementTaskInterface() {
        Assertions.assertTrue(feedback instanceof Task);
    }

    @Test
    public void constructor_Should_ThrowException_When_TitleIsInvalidLength() {
        Assertions.assertThrows(IllegalArgumentException.class,()-> new FeedbackImpl(
                BaseConstants.STORY_MIN_ID,
                BaseConstants.INVALID_TITLE,
                BaseConstants.VALID_DESCRIPTION,
                20));
    }

    @Test
    public void constructor_Should_ThrowException_When_DescriptionLengthIsInvalid() {
        Assertions.assertThrows(IllegalArgumentException.class,()-> new FeedbackImpl(
                BaseConstants.STORY_MIN_ID,
                BaseConstants.VALID_TITLE,
                BaseConstants.INVALID_DESCRIPTION,
                20));
    }


    @Test
    public void constructor_Should_CreateNewFeedback_When_ParametersAreCorrect() {
        Assertions.assertEquals(BaseConstants.VALID_TITLE, feedback.getTitle());
    }
    public static FeedbackImpl initializeTestFeedback() {
        return new FeedbackImpl(
                BaseConstants.STORY_MIN_ID,
                BaseConstants.VALID_TITLE,
                BaseConstants.VALID_DESCRIPTION,
                20);
    }
}
