package com.project.oop.tasksmanagement.models;

import com.project.oop.tasksmanagement.models.contracts.Board;
import com.project.oop.tasksmanagement.models.contracts.Member;
import com.project.oop.tasksmanagement.models.contracts.Team;
import com.project.oop.tasksmanagement.utils.EventLog;
import com.project.oop.tasksmanagement.utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;

public class TeamImpl implements Team {
    private static final String NAME_LENGTH_ERR = "Team name must be between %d and %d symbols.";
    private static final int MIN_NAME_LENGTH = 5;
    private static final int MAX_NAME_LENGTH = 15;
    private static final String NO_MEMBERS_FOUND_IN_TEAM_HEADER = "No members found in team %s.";
    private static final String NO_BOARDS_FOUND_IN_TEAM_HEADER = "No boards found in team %s.";
    private static final String BOARD_DOES_NOT_EXIST = "Board with name %s does not exist in Team %s";
    private static final String TEAM_CREATED_HEADER = "Team %s created.";
    private static final String MEMBER_ADDED_TO_TEAM_HEADER = "Member %s added to team.";
    private static final String BOARD_ADDED_TO_TEAM_HEADER = "Board %s added to team.";
    private String teamName;
    private final ArrayList<Member> members;

    private final ArrayList<Board> boards;
    private final List<EventLog> activityHistory;

    public TeamImpl(String teamName) {
        setTeamName(teamName);
        members = new ArrayList<>();
        boards = new ArrayList<>();
        activityHistory = new ArrayList<>();
        activityHistory.add(new EventLog(String.format(TEAM_CREATED_HEADER, teamName)));
    }


    @Override
    public String getName() {
        return this.teamName;
    }
    //returns team name

    @Override
    public List<Member> getMembers() {
        return new ArrayList<>(members);
    }
    //returns copy of all members in the team

    @Override
    public List<Board> getBoards() {
        return new ArrayList<>(boards);
    }
    //returns copy of all boards in the team


    @Override
    public void addMember(Member member) {
        members.add(member);
        activityHistory.add(new EventLog(String.format(MEMBER_ADDED_TO_TEAM_HEADER, member.getName())));
    }
    //adds a member to the team and logs it into eventHistory

    @Override
    public void addBoard(Board board) {
        boards.add(board);
        activityHistory.add(new EventLog(String.format(BOARD_ADDED_TO_TEAM_HEADER, board.getName())));
    }
    //adds a board to the team

    private void setTeamName(String teamName) {
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
    public String printTeamMembers() {
        StringBuilder result = new StringBuilder();
        int counter = 1;
        if (members.isEmpty()) {
            return result.append(String.format(NO_MEMBERS_FOUND_IN_TEAM_HEADER,getName())).toString();
        }
        for (Member member : members) {
            result.append(counter).append(".").append(member.getName()).append(System.lineSeparator());
            counter++;
        }
        return result.toString();
    }
    //returns a customized list of all members.

    public Board findTeamBoardByName(String boardName){
        for (Board board: boards) {
            if (board.getName().equals(boardName)){
                return board;
            }
        }
        throw new IllegalArgumentException(BOARD_DOES_NOT_EXIST.formatted(boardName,getName()));
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
