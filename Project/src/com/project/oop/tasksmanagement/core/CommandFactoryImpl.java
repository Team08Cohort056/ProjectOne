package com.project.oop.tasksmanagement.core;

import com.project.oop.tasksmanagement.commands.AddBoardCommand;
import com.project.oop.tasksmanagement.commands.AddMemberToTeam;
import com.project.oop.tasksmanagement.commands.CreateMemberCommand;
import com.project.oop.tasksmanagement.commands.CreateTeamCommand;
import com.project.oop.tasksmanagement.commands.contracts.BaseCommand;
import com.project.oop.tasksmanagement.commands.enums.CommandType;
import com.project.oop.tasksmanagement.core.contracts.CommandFactory;
import com.project.oop.tasksmanagement.core.contracts.TaskManagementRepository;
import com.project.oop.tasksmanagement.utils.ParsingHelpers;

public class CommandFactoryImpl implements CommandFactory {

    private static final String INVALID_COMMAND = "Invalid command name: %s!";

    @Override
    public BaseCommand createCommandFromCommandName(String commandTypeAsString,
                                                    TaskManagementRepository taskManagementRepository) {
        CommandType commandType = ParsingHelpers.tryParseEnum(commandTypeAsString,
                CommandType.class);

        switch (commandType) {
            case ADDBOARD:
                return new AddBoardCommand(taskManagementRepository);
            case ADDCOMMENT:
                //TODO
            case REMOVECOMMENT:
                //TODO
            case ADDMEMBERTOTEAM:
                return new AddMemberToTeam(taskManagementRepository);
            case CREATEMEMBER:
                return new CreateMemberCommand(taskManagementRepository);
            case CREATETEAM:
                return new CreateTeamCommand(taskManagementRepository);
            case CREATETASK:
                //TODO
            case CHANGETASK:
                //TODO
            case ASSIGNTASK:
                //TODO
            case UNASSIGNTASK:
                //TODO
            case SHOWALL:
                //TODO
            case SHOWACTIVITY:
                //TODO
            case LISTALL:
                //TODO
            case LISTASSIGNEDTASKS:
                //TODO
        }
        throw new IllegalArgumentException(String.format(INVALID_COMMAND, commandTypeAsString));
    }
}