package com.project.oop.tasksmanagement.core;

import com.project.oop.tasksmanagement.core.contracts.TaskManagementRepository;
import com.project.oop.tasksmanagement.core.exceptions.InvalidUserInputException;
import com.project.oop.tasksmanagement.models.BoardImpl;
import com.project.oop.tasksmanagement.models.MemberImpl;
import com.project.oop.tasksmanagement.models.contracts.*;
import com.project.oop.tasksmanagement.models.enums.Severity;
import com.project.oop.tasksmanagement.models.enums.StorySize;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TaskManagementRepositoryImplTests {
    public static final String VALID_TASK_TITLE = "VALIDTASKTITLE";
    public static final String VALID_TASK_DESCRIPTION = "VALIDTASKDESCRIPTION";
    public static final String VALID_TEAM_NAME = "TESTTEAM";
    public static final String VALID_MEMBER_NAME = "TESTNAME";
    public static final String VALID_BOARD_NAME = "TESTBOARD";
    TaskManagementRepository repository;


    @BeforeEach
    public void repositoryInit() {
        repository = new TaskManagementRepositoryImpl();
    }

    @Test
    void memberExists_Should_ReturnFalse_When_MemberDoesNotExist() {
        Assertions.assertFalse(repository.memberExists(VALID_MEMBER_NAME));
    }

    @Test
    void createBug_Should_IncreaseIDByOne_When_ValidArguments() {
        repository.createBug(VALID_TASK_TITLE, VALID_TASK_DESCRIPTION, Severity.MINOR);
        Assertions.assertEquals(1, repository.getId());
    }

    @Test
    void createStory_Should_IncreaseIDByOne_When_ValidArguments() {
        repository.createStory(VALID_TASK_TITLE, VALID_TASK_DESCRIPTION, StorySize.SMALL);
        Assertions.assertEquals(1, repository.getId());
    }

    @Test
    void createFeedback_Should_IncreaseIDByOne_When_ValidArguments() {
        repository.createFeedback(VALID_TASK_TITLE, VALID_TASK_DESCRIPTION, 42);
        Assertions.assertEquals(1, repository.getId());
    }

    @Test
    void findTaskById_Should_FindTaskByID_When_ValidArguments() {
        Feedback testFeedback = repository.createFeedback(VALID_TASK_TITLE, VALID_TASK_DESCRIPTION, 42);
        Story testStory = repository.createStory(VALID_TASK_TITLE, VALID_TASK_DESCRIPTION, StorySize.SMALL);
        Bug testBug = repository.createBug(VALID_TASK_TITLE, VALID_TASK_DESCRIPTION, Severity.MINOR);
        repository.addTask(testFeedback);
        repository.addTask(testStory);
        repository.addTask(testBug);
        Assertions.assertEquals(repository.findTaskById(1), testFeedback);
    }
    @Test
    void findTaskById_Should_ThrowException_When_NoTaskFound() {
        Feedback testFeedback = repository.createFeedback(VALID_TASK_TITLE, VALID_TASK_DESCRIPTION, 42);
        Story testStory = repository.createStory(VALID_TASK_TITLE, VALID_TASK_DESCRIPTION, StorySize.SMALL);
        Bug testBug = repository.createBug(VALID_TASK_TITLE, VALID_TASK_DESCRIPTION, Severity.MINOR);
        repository.addTask(testFeedback);
        repository.addTask(testStory);
        repository.addTask(testBug);
        Assertions.assertThrows(IllegalArgumentException.class, ()-> repository.findTaskById(4));
    }

    @Test
    void findTeamByName_Should_ThrowException_WhenTeamDoesNotExist() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> repository.findTeamByName(VALID_TEAM_NAME));
    }

    @Test
    void findTeamByName_Should_ReturnTeam_WhenTeamWithInputNameExists() {
        Team team = repository.createTeam(VALID_TEAM_NAME);
        repository.addTeam(team);
        Assertions.assertEquals(team, repository.findTeamByName(VALID_TEAM_NAME));
    }
    @Test
    void findMemberByName_Should_ThrowException_WhenMemberDoesNotExist() {
        Assertions.assertThrows(InvalidUserInputException.class, () -> repository.findMemberByName(VALID_TEAM_NAME));
    }

    @Test
    void findMemberByName_Should_ReturnTeam_WhenMemberWithInputNameExists() {
        Member member = repository.createMember(VALID_MEMBER_NAME);
        repository.addMember(member);
        Assertions.assertEquals(member, repository.findMemberByName(VALID_MEMBER_NAME));
    }
    @Test
    void findBoardByName_Should_ThrowException_WhenBoardDoesNotExist() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> repository.findBoardByName(VALID_BOARD_NAME));
    }

    @Test
    void findBoardByName_Should_ReturnBoard_WhenBoardWithInputNameExists() {
        Board board = repository.createBoard(VALID_BOARD_NAME);
        Team team = repository.createTeam(VALID_TEAM_NAME);
        team.addBoard(board);
        repository.addTeam(team);
        Assertions.assertEquals(board, repository.findBoardByName(VALID_BOARD_NAME));
    }

}
