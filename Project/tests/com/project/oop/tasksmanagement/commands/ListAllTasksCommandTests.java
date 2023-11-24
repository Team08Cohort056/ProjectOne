package com.project.oop.tasksmanagement.commands;

import com.project.oop.tasksmanagement.commands.listing.ListAllTasksCommand;
import com.project.oop.tasksmanagement.commands.showcommands.ShowAllCommand;
import com.project.oop.tasksmanagement.core.TaskManagementRepositoryImpl;
import com.project.oop.tasksmanagement.core.TaskManagementRepositoryImplTests;
import com.project.oop.tasksmanagement.core.contracts.TaskManagementRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ListAllTasksCommandTests {
    public static final String INVALID_COMMAND = "TESTCOMMAND";
    TaskManagementRepository repository;
    List<String> commands;
    ListAllTasksCommand listTAllCommand;
    @BeforeEach
    public void before() {
        repository = new TaskManagementRepositoryImpl();
        commands = new ArrayList<>();
        listTAllCommand= new ListAllTasksCommand(repository);
    }
    @Test
    public void constructor_Should_ThrowException_When_ArgumentCountDifferentThanExpected(){
        commands.add("LISTALLTASKS");
        commands.add("LISTALLTASKS");
        commands.add("LISTALLTASKS");

        Assertions.assertThrows(IllegalArgumentException.class, ()->listTAllCommand.execute(commands));
    }
    @Test
    public void constructor_Should_ThrowException_When_InvalidCommand() {
        commands.add(INVALID_COMMAND);
        Assertions.assertThrows(IllegalArgumentException.class, () -> listTAllCommand.execute(commands));
    }
    @Test
    public void ListAllTasks_Should_ExecuteSort_When_ValidArguments() {
        Assertions.assertEquals(listTAllCommand.execute(commands),listTAllCommand.listAllTasksSorted());
    }
    @Test
    public void ListAllTasks_Should_ExecuteFilter_When_ValidArguments() {
        repository.addTask(repository.createFeedback(TaskManagementRepositoryImplTests.VALID_TASK_TITLE, TaskManagementRepositoryImplTests.VALID_TASK_DESCRIPTION, 42));
        commands.add("TITLE");
        commands.add(TaskManagementRepositoryImplTests.VALID_TASK_TITLE);
        Assertions.assertEquals(listTAllCommand.execute(commands),listTAllCommand.filterAllTasksByTitle(TaskManagementRepositoryImplTests.VALID_TASK_TITLE));
    }

}
