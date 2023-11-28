package com.project.oop.tasksmanagement.models.tasks;

import com.project.oop.tasksmanagement.models.contracts.Story;
import com.project.oop.tasksmanagement.models.enums.StorySize;
import com.project.oop.tasksmanagement.utils.BaseConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StoryImplTests {

    @Test
    public void storyImpl_Should_ImplementStoryInterface() {
        // Arrange, Act
        StoryImpl story = initializeTestStory();
        // Assert
        Assertions.assertTrue(story instanceof Story);
    }

    @Test
    public void storyImpl_Should_ImplementTaskInterface() {
        // Arrange, Act
        StoryImpl story = initializeTestStory();
        // Assert
        Assertions.assertTrue(story instanceof Story);
    }


    @Test
    public void constructor_Should_ThrowException_When_TitleIsInvalidLength() {
        // Arrange, Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new StoryImpl(
                        BaseConstants.STORY_MIN_ID,
                        BaseConstants.INVALID_TITLE,
                        BaseConstants.VALID_DESCRIPTION,
                        StorySize.SMALL));
    }

    @Test
    public void constructor_Should_ThrowException_When_DescriptionLengthIsInvalid() {
        // Arrange, Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new StoryImpl(
                        BaseConstants.STORY_MIN_ID,
                        BaseConstants.VALID_TITLE,
                        BaseConstants.INVALID_DESCRIPTION,
                        StorySize.SMALL));
    }

    @Test
    public void constructor_Should_CreateNewStory_When_ParametersAreCorrect() {
        // Arrange, Act
        StoryImpl story = initializeTestStory();

        // Assert
        Assertions.assertEquals(BaseConstants.VALID_TITLE, story.getTitle());
    }

    public static StoryImpl initializeTestStory() {
        return new StoryImpl(
                BaseConstants.STORY_MIN_ID,
                BaseConstants.VALID_TITLE,
                BaseConstants.VALID_DESCRIPTION,
                StorySize.SMALL);
    }
}
