package com.project.oop.tasksmanagement.commands;

import com.project.oop.tasksmanagement.commands.addcreatecommands.AddTaskCommand;
import com.project.oop.tasksmanagement.core.TaskManagementRepositoryImpl;
import com.project.oop.tasksmanagement.core.contracts.TaskManagementRepository;
import com.project.oop.tasksmanagement.models.contracts.Board;
import com.project.oop.tasksmanagement.models.contracts.Team;
import com.project.oop.tasksmanagement.utils.BaseConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class AddTaskCommandTests {
    TaskManagementRepository taskManagementRepository;
    AddTaskCommand addTaskCommand;
    Team team1;
    Board board;
    List<String> commands;

    @BeforeEach
    public void before(){
        taskManagementRepository = new TaskManagementRepositoryImpl();
        addTaskCommand = new AddTaskCommand(taskManagementRepository);
        team1 = taskManagementRepository.createTeam("team1");
        board = taskManagementRepository.createBoard("board");
        taskManagementRepository.addTeam(team1);
        team1.addBoard(board);
        commands = new ArrayList<>();
    }
    @Test
    public void AddTaskCommand_Should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> addTaskCommand.execute(commands));
    }
    @Test
    public void AddTaskCommand_Should_CreateAndAddStory_When_ValidArguments(){
        commands.add("team1");
        commands.add("board");
        commands.add("story");
        commands.add(BaseConstants.VALID_TITLE);
        commands.add(BaseConstants.VALID_DESCRIPTION);
        commands.add("large");
        addTaskCommand.execute(commands);
        Assertions.assertEquals(1,taskManagementRepository.getAllTasks().size());
    }
    @Test
    public void AddTaskCommand_Should_CreateAndAddFeedback_When_ValidArguments(){
        commands.add("team1");
        commands.add("board");
        commands.add("feedback");
        commands.add(BaseConstants.VALID_TITLE);
        commands.add(BaseConstants.VALID_DESCRIPTION);
        commands.add("5");
        addTaskCommand.execute(commands);
        Assertions.assertEquals(1,taskManagementRepository.getAllTasks().size());
    }
    @Test
    public void AddTaskCommand_Should_CreateAndAddBug_When_ValidArguments(){
        commands.add("team1");
        commands.add("board");
        commands.add("bug");
        commands.add(BaseConstants.VALID_TITLE);
        commands.add(BaseConstants.VALID_DESCRIPTION);
        commands.add("major");
        addTaskCommand.execute(commands);
        Assertions.assertEquals(1,taskManagementRepository.getAllTasks().size());
    }

}
