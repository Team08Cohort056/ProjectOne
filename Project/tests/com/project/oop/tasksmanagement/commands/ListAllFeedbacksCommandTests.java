package com.project.oop.tasksmanagement.commands;

import com.project.oop.tasksmanagement.commands.listing.ListAllFeedbacksCommand;
import com.project.oop.tasksmanagement.commands.listing.ListAllTasksCommand;
import com.project.oop.tasksmanagement.core.TaskManagementRepositoryImpl;
import com.project.oop.tasksmanagement.core.TaskManagementRepositoryImplTests;
import com.project.oop.tasksmanagement.core.contracts.TaskManagementRepository;
import com.project.oop.tasksmanagement.models.enums.Status;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ListAllFeedbacksCommandTests {
    public static final String INVALID_COMMAND = "TESTCOMMAND";
    TaskManagementRepository repository;
    List<String> commands;
    ListAllFeedbacksCommand listAllFeedbacksCommand;
    @BeforeEach
    public void before() {
        repository = new TaskManagementRepositoryImpl();
        commands = new ArrayList<>();
        listAllFeedbacksCommand = new ListAllFeedbacksCommand(repository);
    }
    @Test
    public void constructor_Should_ThrowException_When_ArgumentCountDifferentThanExpected(){
        commands.add("LISTALLFEEDBACKS");
        commands.add("LISTALLFEEDBACKS");
        commands.add("LISTALLFEEDBACKS");

        Assertions.assertThrows(IllegalArgumentException.class, ()->listAllFeedbacksCommand.execute(commands));
    }
    @Test
    public void constructor_Should_ThrowException_When_InvalidCommand() {
        commands.add(INVALID_COMMAND);
        Assertions.assertThrows(IllegalArgumentException.class, () -> listAllFeedbacksCommand.execute(commands));
    }
    @Test
    public void ListAllTasks_Should_ExecuteSort_When_ValidArguments() {
        Assertions.assertEquals(listAllFeedbacksCommand.execute(commands),listAllFeedbacksCommand.listAllFeedbacksSorted());
    }
    @Test
    public void ListAllTasks_Should_ExecuteFilter_When_ValidArguments() {
        repository.addTask(repository.createFeedback(TaskManagementRepositoryImplTests.VALID_TASK_TITLE, TaskManagementRepositoryImplTests.VALID_TASK_DESCRIPTION, 42));
        commands.add("STATUS");
        commands.add("new");
        Assertions.assertEquals(listAllFeedbacksCommand.execute(commands),listAllFeedbacksCommand.filterAllFeedbacksByStatus(Status.NEW));
    }
}
