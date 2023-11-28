package com.project.oop.tasksmanagement.commands;

import com.project.oop.tasksmanagement.commands.enums.CommandType;
import com.project.oop.tasksmanagement.core.TaskManagementRepositoryImpl;
import com.project.oop.tasksmanagement.core.contracts.TaskManagementRepository;
import com.project.oop.tasksmanagement.utils.BaseConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class HelpCommandTests {
    public static final String HELP_HELPMESSAGE = """
            Shows the user instructions on using the application. Command takes no parameters or 1 parameter:
            [1]Name of command to receive instructions on.
            """;
    TaskManagementRepository repository;
    HelpCommand helpCommand;
    List<String> commands;
    @BeforeEach
    public void before() {
        repository = new TaskManagementRepositoryImpl();
         helpCommand = new HelpCommand(repository);
        commands = new ArrayList<>();
    }
    @Test
    public void HelpCommand_Should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        commands.add(CommandType.HELP.toString());
        commands.add(CommandType.HELP.toString());
        commands.add(CommandType.HELP.toString());
        Assertions.assertThrows(IllegalArgumentException.class, () -> helpCommand.execute(commands));
    }
    @Test
    public void HelpCommand_Should_ThrowException_When_CommandDoesNotExist() {
        commands.add(BaseConstants.VALID_DESCRIPTION);
        Assertions.assertThrows(IllegalArgumentException.class, () -> helpCommand.execute(commands));
    }
    @Test
    public void HelpCommand_Should_ReturnInstructions_When_ValidInput(){
        commands.add(CommandType.HELP.toString());
        Assertions.assertEquals(HELP_HELPMESSAGE, helpCommand.execute(commands));
    }
}
