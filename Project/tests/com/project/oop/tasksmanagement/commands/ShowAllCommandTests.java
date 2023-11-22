package com.project.oop.tasksmanagement.commands;

import com.project.oop.tasksmanagement.commands.showcommands.ShowAllCommand;
import com.project.oop.tasksmanagement.core.TaskManagementRepositoryImpl;
import com.project.oop.tasksmanagement.core.contracts.TaskManagementRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ShowAllCommandTests {
    public static final String INVALID_COMMAND = "TESTCOMMAND";
    TaskManagementRepository repository;
    List<String> commands;
    ShowAllCommand showAllCommand;
    @BeforeEach
    public void before() {
        repository = new TaskManagementRepositoryImpl();
        commands = new ArrayList<>();
        showAllCommand= new ShowAllCommand(repository);
    }


    @Test
    public void ShowAllCommand_Should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> showAllCommand.execute(commands));
    }
    @Test
    public void ShowAllCommand_Should_ThrowException_When_InvalidCommand() {
        commands.add(INVALID_COMMAND);
        Assertions.assertThrows(IllegalArgumentException.class, () -> showAllCommand.execute(commands));
    }
    @Test
    public void ShowAllCommand_Should_ExecuteCollectionPrintCommand_When_ValidArguments() {
        commands.add("TEAMS");
        Assertions.assertEquals(showAllCommand.execute(commands),repository.printTeams());
        commands.remove(0);
        commands.add("BOARDS");
        Assertions.assertEquals(showAllCommand.execute(commands),repository.printBoards());
        commands.remove(0);
        commands.add("TASKS");
        Assertions.assertEquals(showAllCommand.execute(commands),repository.printTasks());
        commands.remove(0);
        commands.add("MEMBERS");
        Assertions.assertEquals(showAllCommand.execute(commands),repository.printMembers());

    }


}
