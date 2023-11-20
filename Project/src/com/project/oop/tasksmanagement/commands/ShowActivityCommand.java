package com.project.oop.tasksmanagement.commands;

import com.project.oop.tasksmanagement.commands.contracts.BaseCommand;
import com.project.oop.tasksmanagement.core.contracts.TaskManagementRepository;
import com.project.oop.tasksmanagement.models.contracts.ActivityHistory;
import com.project.oop.tasksmanagement.utils.EventLog;
import com.project.oop.tasksmanagement.utils.ParsingHelpers;
import com.project.oop.tasksmanagement.utils.ValidationHelpers;

import java.util.List;

public class ShowActivityCommand implements BaseCommand {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    public static final String INVALID_COMMAND = "Invalid Type.";
    public static final String NO_ACTIVITY_FOUND_ERR = "No activity found for %s %s!";
    public static final String NOT_VALID_ID_ERR = "Please provide a valid ID.";

    private final TaskManagementRepository repository;

    public ShowActivityCommand(TaskManagementRepository taskManagementRepository) {
        this.repository = taskManagementRepository;
    }

    @Override
    public String execute(List<String> commands) {
        ValidationHelpers.validateArgumentsCount(commands,EXPECTED_NUMBER_OF_ARGUMENTS);
        String type = commands.get(0).toUpperCase();
        String target = commands.get(1);
        return printActivity(type,target);
    }

    public String printActivity(String type, String target){


        switch (type){
            case "TEAM":
                List<EventLog> teamActivityHistory = repository.findTeamByName(target).getActivityHistory();
                StringBuilder teamResult = new StringBuilder();
                if (teamActivityHistory.isEmpty()){
                    return String.format(NO_ACTIVITY_FOUND_ERR,type,target);
                }
                for (EventLog eventLog: teamActivityHistory) {
                    teamResult.append(eventLog);
                }

                return teamResult.toString();
            case "MEMBER":
                List<EventLog> memberActivityHistory = repository.findMemberByName(target).getActivityHistory();
                StringBuilder memberResult = new StringBuilder();
                if (memberActivityHistory.isEmpty()){
                    return String.format(NO_ACTIVITY_FOUND_ERR,type,target);
                }
                for (EventLog eventLog: memberActivityHistory) {
                    memberResult.append(eventLog);
                }
                return memberResult.toString();
            case "BOARD":
                List<EventLog> boardActivityHistory = repository.findBoardByName(target).getActivityHistory();
                StringBuilder boardResult = new StringBuilder();
                if (boardActivityHistory.isEmpty()){
                    return String.format(NO_ACTIVITY_FOUND_ERR,type,target);
                }
                for (EventLog eventLog: boardActivityHistory) {
                    boardResult.append(eventLog);
                }
                return boardResult.toString();
            case "TASK":
                List<EventLog> taskActivityHistory = repository.findTaskById(ParsingHelpers.tryParseInt(target, NOT_VALID_ID_ERR)).getActivityHistory();
                StringBuilder taskResult = new StringBuilder();
                if (taskActivityHistory.isEmpty()){
                    return String.format(NO_ACTIVITY_FOUND_ERR,type,target);
                }
                for (EventLog eventLog: taskActivityHistory) {
                    taskResult.append(eventLog);
                }

                return taskResult.toString();
            default:
                return INVALID_COMMAND;
        }
    }
}
