package com.project.oop.tasksmanagement.models.tasks;

import com.project.oop.tasksmanagement.models.contracts.Bug;
import com.project.oop.tasksmanagement.models.contracts.Task;
import com.project.oop.tasksmanagement.models.enums.Severity;
import com.project.oop.tasksmanagement.models.enums.Status;
import com.project.oop.tasksmanagement.utils.BaseConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BugImplTests {
    Bug bug;
    @BeforeEach
    public void before(){
       bug = initializeTestBug();
    }
    @Test
    public void BugImpl_Should_ImplementBugInterface(){
        Assertions.assertTrue(bug instanceof Bug);
    }
    @Test
    public void BugImpl_Should_ImplementTaskInterface() {
        Assertions.assertTrue(bug instanceof Task);
    }
    @Test
    public void constructor_Should_ThrowException_When_TitleIsInvalidLength() {
        Assertions.assertThrows(IllegalArgumentException.class,()-> new BugImpl(
                BaseConstants.STORY_MIN_ID,
                BaseConstants.INVALID_TITLE,
                BaseConstants.VALID_DESCRIPTION,
                Severity.MINOR));
    }

    @Test
    public void constructor_Should_ThrowException_When_DescriptionLengthIsInvalid() {
        Assertions.assertThrows(IllegalArgumentException.class,()-> new BugImpl(
                BaseConstants.STORY_MIN_ID,
                BaseConstants.VALID_TITLE,
                BaseConstants.INVALID_DESCRIPTION,
                Severity.MINOR));
    }

    @Test
    public void constructor_Should_CreateNewBug_When_ParametersAreCorrect() {
        Assertions.assertEquals(BaseConstants.VALID_TITLE, bug.getTitle());
    }

    @Test
    public void addStepToReproduce_Should_addStepsForReproduction(){
        bug.addStepToReproduce("step");
        Assertions.assertEquals("step",bug.getStepsToReproduce());
    }

    @Test
    public void changeBugSeverity_Should_ChangeTheSeverityOfTheBug_When_NewSeverityIsCorrect(){
        bug.changeBugSeverity(Severity.CRITICAL);
        Assertions.assertEquals(Severity.CRITICAL,bug.getSeverity());
    }

    @Test
    public void changeBugSeverity_Should_ThrowException_When_NewSeverityIsIncorrect(){
        Assertions.assertThrows(IllegalArgumentException.class,()-> bug.changeBugSeverity(Severity.valueOf("Done")));
    }

    @Test
    public void changeStatus_Should_ChangeTheSeverityOfTheBug_When_NewSeverityIsCorrect(){
        bug.changeBugStatus(Status.DONE);
        Assertions.assertEquals(Status.DONE,bug.getStatus());
    }

    @Test
    public void changeStatus_Should_ThrowException_When_NewSeverityIsIncorrect(){
        Assertions.assertThrows(IllegalArgumentException.class,()-> bug.changeBugStatus(Status.valueOf("Critical")));
    }


    public static Bug initializeTestBug() {
        return new BugImpl(
                BaseConstants.STORY_MIN_ID,
                BaseConstants.VALID_TITLE,
                BaseConstants.VALID_DESCRIPTION,
                Severity.MINOR);
    }
}
