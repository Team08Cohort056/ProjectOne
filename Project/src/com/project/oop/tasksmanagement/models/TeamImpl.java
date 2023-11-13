package com.project.oop.tasksmanagement.models;

import com.project.oop.tasksmanagement.models.contracts.Board;
import com.project.oop.tasksmanagement.models.contracts.Team;
import com.project.oop.tasksmanagement.utils.EventLog;
import com.project.oop.tasksmanagement.utils.ValidationHelpers;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;

public class TeamImpl implements Team {
    private static final String NAME_LENGTH_ERR = "Team name must be between %d and %d symbols.";
    private static final int MIN_NAME_LENGTH = 5;
    private static final int MAX_NAME_LENGTH = 15;
    private String teamName;
    private final ArrayList<Member> members = new ArrayList<>();

    private final ArrayList<Board> boards = new ArrayList<>();
    private final List<EventLog> activityHistory = new ArrayList<>();

    public TeamImpl(String teamName) {
        setTeamName(teamName);
        activityHistory.add(new EventLog(String.format("Team %s created.", teamName)));
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
        activityHistory.add(new EventLog(String.format("Member %s added to team.", member.getName())));
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


}
