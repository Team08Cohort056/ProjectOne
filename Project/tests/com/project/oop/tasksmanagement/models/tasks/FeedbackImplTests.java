package com.project.oop.tasksmanagement.models.tasks;

import com.project.oop.tasksmanagement.commands.HelpCommand;
import com.project.oop.tasksmanagement.core.TaskManagementRepositoryImpl;
import com.project.oop.tasksmanagement.models.contracts.Feedback;
import com.project.oop.tasksmanagement.models.contracts.Story;
import com.project.oop.tasksmanagement.models.contracts.Task;
import com.project.oop.tasksmanagement.models.enums.StorySize;
import com.project.oop.tasksmanagement.models.tasks.FeedbackImpl;
import com.project.oop.tasksmanagement.models.tasks.StoryImpl;
import com.project.oop.tasksmanagement.utils.TaskBaseConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

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
                TaskBaseConstants.STORY_MIN_ID,
                TaskBaseConstants.INVALID_TITLE,
                TaskBaseConstants.VALID_DESCRIPTION,
                20));
    }

    @Test
    public void constructor_Should_ThrowException_When_DescriptionLengthIsInvalid() {
        Assertions.assertThrows(IllegalArgumentException.class,()-> new FeedbackImpl(
                TaskBaseConstants.STORY_MIN_ID,
                TaskBaseConstants.VALID_TITLE,
                TaskBaseConstants.INVALID_DESCRIPTION,
                20));
    }


    @Test
    public void constructor_Should_CreateNewFeedback_When_ParametersAreCorrect() {
        Assertions.assertEquals(TaskBaseConstants.VALID_TITLE, feedback.getTitle());
    }
    public static FeedbackImpl initializeTestFeedback() {
        return new FeedbackImpl(
                TaskBaseConstants.STORY_MIN_ID,
                TaskBaseConstants.VALID_TITLE,
                TaskBaseConstants.VALID_DESCRIPTION,
                20);
    }
}
