package com.project.oop.tasksmanagement.commands;

import com.project.oop.tasksmanagement.core.TaskManagementRepositoryImpl;
import com.project.oop.tasksmanagement.core.contracts.TaskManagementRepository;
import com.project.oop.tasksmanagement.models.*;
import com.project.oop.tasksmanagement.models.contracts.Team;
import com.project.oop.tasksmanagement.utils.UtilsTests;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.IllformedLocaleException;
import java.util.List;

public class CreateTeamCommandTests {
    TaskManagementRepository repository;
    CreateTeamCommand createTeamCommand;
    List<String> commands;
    @BeforeEach
    public void before() {
        repository = new TaskManagementRepositoryImpl();
        createTeamCommand = new CreateTeamCommand(repository);
        commands = new ArrayList<>();
    }

    @Test
    public void CreateTeamCommand_Should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> createTeamCommand.execute(commands));
    }
    @Test
    public void CreateTeamCommand_Should_ThrowException_When_TeamAlreadyExists(){
        Team team = new TeamImpl(TeamImplTests.VALID_NAME);
        repository.addTeam(team);
        commands.add(TeamImplTests.VALID_NAME);
        Assertions.assertThrows(IllegalArgumentException.class,() -> createTeamCommand.execute(commands));
    }
    @Test
    public void CreateTeamCommand_Should_CreateTeam_When_ValidArguments(){
        commands.add(TeamImplTests.VALID_NAME);
        createTeamCommand.execute(commands);
        Assertions.assertEquals(1,repository.getTeams().size());
    }

}
