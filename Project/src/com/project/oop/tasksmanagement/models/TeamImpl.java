package com.project.oop.tasksmanagement.models;

import com.project.oop.tasksmanagement.models.contracts.Board;
import com.project.oop.tasksmanagement.models.contracts.Developer;
import com.project.oop.tasksmanagement.models.contracts.Team;
import com.project.oop.tasksmanagement.utils.EventLog;
import com.project.oop.tasksmanagement.utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;

public class TeamImpl implements Team {
    private static final String NAME_LENGTH_ERR = "Team name must be between %d and %d symbols.";
    private static final int MIN_NAME_LENGTH = 5;
    private static final int MAX_NAME_LENGTH = 15;
    public static final String NO_DEVELOPERS_FOUND_IN_TEAM_HEADER = "No developers found in team %s.";
    public static final String NO_BOARDS_FOUND_IN_TEAM_HEADER = "No boards found in team %s.";
    public static final String BOARD__DOES_NOT_EXIST = "Board with name %s does not exist in Team %s";
    private String teamName;
    private final ArrayList<Developer> developers;

    private final ArrayList<Board> boards;
    private final List<EventLog> activityHistory;

    public TeamImpl(String teamName) {
        setTeamName(teamName);
        developers = new ArrayList<>();
        boards = new ArrayList<>();
        activityHistory = new ArrayList<>();
        activityHistory.add(new EventLog(String.format("Team %s created.", teamName)));
    }


    @Override
    public String getName() {
        return this.teamName;
    }
    //returns team name

    @Override
    public List<Developer> getDevelopers() {
        return new ArrayList<>(developers);
    }
    //returns copy of all members in the team

    @Override
    public List<Board> getBoards() {
        return new ArrayList<>(boards);
    }
    //returns copy of all boards in the team


    @Override
    public void addDeveloper(Developer developer) {
        developers.add(developer);
        activityHistory.add(new EventLog(String.format("Member %s added to team.", developer.getName())));
    }
    //adds a member to the team and logs it into eventHistory

    @Override
    public void addBoard(Board board) {
        boards.add(board);
        activityHistory.add(new EventLog(String.format("Board %s added to team.", board.getName())));
    }
    //adds a board to the team

    public void setTeamName(String teamName) {
        ValidationHelpers.validateStringLength(teamName,MIN_NAME_LENGTH,MAX_NAME_LENGTH,
                NAME_LENGTH_ERR.formatted(MIN_NAME_LENGTH,MAX_NAME_LENGTH));
        this.teamName = teamName;
    }
    //sets name of team if arguments are valid.

    @Override
    public List<EventLog> getActivityHistory() {
        return new ArrayList<>(activityHistory);
    }
    //returns a copy of activityHistory
    public String printTeamDevelopers() {
        StringBuilder result = new StringBuilder();
        int counter = 1;
        if (developers.isEmpty()) {
            return result.append(String.format(NO_DEVELOPERS_FOUND_IN_TEAM_HEADER,getName())).toString();
        }
        for (Developer developer : developers) {
            result.append(counter).append(".").append(developer.getName()).append(System.lineSeparator());
            counter++;
        }
        return result.toString();
    }
    //returns a customized list of all developers.

    public Board findTeamBoardByName(String boardName){
        for (Board board: boards) {
            if (board.getName().equals(boardName)){
                return board;
            }
        }
        throw new IllegalArgumentException(BOARD__DOES_NOT_EXIST.formatted(boardName,getName()));
    }
    @Override
    public String printTeamBoards() {
        StringBuilder result = new StringBuilder();
        int counter = 1;
        if (boards.isEmpty()) {
            return result.append(String.format(NO_BOARDS_FOUND_IN_TEAM_HEADER,getName())).toString();
        }
        for (Board board : boards) {
            result.append(counter).append(".").append(board.getName()).append(System.lineSeparator());
            counter++;
        }
        return result.toString();
    }
    //returns a customized list of all boards.
}
