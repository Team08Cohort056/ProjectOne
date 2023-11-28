package com.project.oop.tasksmanagement.commands;

import com.project.oop.tasksmanagement.commands.showcommands.ShowActivityCommand;
import com.project.oop.tasksmanagement.core.TaskManagementRepositoryImpl;
import com.project.oop.tasksmanagement.core.contracts.TaskManagementRepository;
import com.project.oop.tasksmanagement.models.TeamImpl;
import com.project.oop.tasksmanagement.models.contracts.Team;
import com.project.oop.tasksmanagement.utils.BaseConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.project.oop.tasksmanagement.models.TeamImplTests.VALID_NAME;

public class ShowActivityCommandTests {
    TaskManagementRepository repository;
    ShowActivityCommand showActivityCommand;
    List<String> commands;
    @BeforeEach
    public void before() {
        repository = new TaskManagementRepositoryImpl();
        showActivityCommand = new ShowActivityCommand(repository);
        commands = new ArrayList<>();
    }

    @Test
    public void ShowActivityCommand_Should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> showActivityCommand.execute(commands));
    }
    @Test
    public void ShowActivityCommand_Should_ThrowException_When_CommandDoesNotExist() {
        commands.add(BaseConstants.VALID_DESCRIPTION);
        Assertions.assertThrows(IllegalArgumentException.class, () -> showActivityCommand.execute(commands));
    }

    @Test
    public void ShowActivityCommand_Should_Execute_When_ValidArguments() {
        repository.addTeam(initializeTestTeam());
        commands.add("TEAM");
        commands.add(VALID_NAME);
        Assertions.assertDoesNotThrow(()-> showActivityCommand.execute(commands));
    }
    public Team initializeTestTeam(){
        return new TeamImpl(VALID_NAME);
    }

}
