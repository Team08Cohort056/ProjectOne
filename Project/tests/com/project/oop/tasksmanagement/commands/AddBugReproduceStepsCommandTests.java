package com.project.oop.tasksmanagement.commands;

import com.project.oop.tasksmanagement.commands.addcreatecommands.AddBugReproduceStepsCommand;
import com.project.oop.tasksmanagement.core.TaskManagementRepositoryImpl;
import com.project.oop.tasksmanagement.core.contracts.TaskManagementRepository;
import com.project.oop.tasksmanagement.models.contracts.Bug;
import com.project.oop.tasksmanagement.models.enums.Severity;
import com.project.oop.tasksmanagement.utils.BaseConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class AddBugReproduceStepsCommandTests {
    TaskManagementRepository taskManagementRepository;
    AddBugReproduceStepsCommand addBugReproduceStepsCommand;
    List<String> commands;
    Bug bug;
    @BeforeEach
    public void before(){
        taskManagementRepository = new TaskManagementRepositoryImpl();
        addBugReproduceStepsCommand = new AddBugReproduceStepsCommand(taskManagementRepository);
        commands = new ArrayList<>();
        bug = taskManagementRepository.createBug(BaseConstants.VALID_TITLE,BaseConstants.VALID_DESCRIPTION, Severity.MINOR);
    }

    @Test
    public void AddBugReproduceStepsCommand_Should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> addBugReproduceStepsCommand.execute(commands));
    }
    @Test
    public void AddBugToReproduceStepsCommand_Should_ThrowException_When_BugWithThisIdDoesNotExist(){
        commands.add("0");
        commands.add("step");
        Assertions.assertThrows(IllegalArgumentException.class, ()-> addBugReproduceStepsCommand.execute(commands));
    }
    @Test
    public void AddBugToReproduceStepsCommand_Should_AddReproduceStepsToBug_When_ValidArguments(){
        commands.add("1");
        commands.add("step");
        addBugReproduceStepsCommand.execute(commands);
        Assertions.assertEquals("step",bug.getStepsToReproduce());
    }
}
