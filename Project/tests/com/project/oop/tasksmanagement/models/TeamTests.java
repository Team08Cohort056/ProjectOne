package com.project.oop.tasksmanagement.models;

import com.project.oop.tasksmanagement.models.contracts.Board;
import com.project.oop.tasksmanagement.models.contracts.Developer;
import com.project.oop.tasksmanagement.models.contracts.Team;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TeamTests {

    public static final String INVALID_NAME = "TEST";
    public static final String VALID_NAME = "VALIDTEST";

    @Test
    public void constructor_Should_ThrowException_When_NameInvalidLength() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new TeamImpl(INVALID_NAME));
    }

    @Test
    public void constructor_Should_CreateTeam_When_NameValidLength() {
        Team team = initializeTestTeam();
        Assertions.assertEquals(VALID_NAME,team.getName());
    }
    @Test
    public void constructor_Should_CreateNewEventLogList_When_ParametersAreCorrect() {
        Team team = initializeTestTeam();
        Assertions.assertEquals(1,team.getActivityHistory().size());
    }
    @Test
    public void addDeveloper_ShouldAdd_Developer_To_ListOfDevelopers(){
        Team team = initializeTestTeam();
        Developer developer =initializeTestDeveloper();
        team.addDeveloper(developer);
        Assertions.assertEquals(team.getDevelopers().get(0),developer);
    }
    @Test
    public void addBoard_ShouldAdd_Board_To_ListOfBoards(){
        Team team = initializeTestTeam();
        Board board = initializeTestBoard();
        team.addBoard(board);
        Assertions.assertEquals(team.getBoards().get(0),board);
    }

    @Test
    public void findTeamBoardByName_ShouldReturn_Board_ByName(){
        Team team = initializeTestTeam();
        Board board = initializeTestBoard();
        team.addBoard(board);
        Assertions.assertEquals(team.findTeamBoardByName(VALID_NAME),team.getBoards().get(0));
    }
    @Test
    public void findTeamBoardByName_Should_ThrowException_When_BoardDoesNotExist(){
        Team team = initializeTestTeam();
        Assertions.assertThrows(IllegalArgumentException.class,()->team.findTeamBoardByName(TeamTests.VALID_NAME));
    }
    public Team initializeTestTeam(){
        return new TeamImpl(VALID_NAME);
    }
    public Developer initializeTestDeveloper(){
        return new DeveloperImpl(VALID_NAME);
    }
    public Board initializeTestBoard(){
        return new BoardImpl(VALID_NAME);
    }

}

