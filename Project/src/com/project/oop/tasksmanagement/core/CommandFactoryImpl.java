package com.project.oop.tasksmanagement.core;

import com.project.oop.tasksmanagement.commands.*;
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
                return new AddCommentCommand(taskManagementRepository);
            case REMOVECOMMENT:
                return new RemoveCommentCommand(taskManagementRepository);
            case ADDMEMBERTOTEAM:
                return new AddMemberToTeam(taskManagementRepository);
            case CREATEMEMBER:
                return new CreateMemberCommand(taskManagementRepository);
            case CREATETEAM:
                return new CreateTeamCommand(taskManagementRepository);
            case ADDTASK:
                return new AddTaskCommand(taskManagementRepository);
            case CHANGETASK:
                return new ChangeTaskCommand(taskManagementRepository);
            case ASSIGNTASK:
                return new AssignTaskCommand(taskManagementRepository);
            case UNASSIGNTASK:
                return new UnAssignTaskCommand(taskManagementRepository);
            case SHOWTEAMMEMBERS:
                return new ShowTeamMembers(taskManagementRepository);
            case SHOWTEAMBOARDS:
                return new ShowTeamBoards(taskManagementRepository);
            case SHOWALL:
                return new ShowAllCommand(taskManagementRepository);
            case SHOWACTIVITY:
                return new ShowActivityCommand(taskManagementRepository);
            case HELP:
                return new HelpCommand(taskManagementRepository);
            case LISTALL:
                //TODO
            case LISTASSIGNEDTASKS:
                //TODO
        }
        throw new IllegalArgumentException(String.format(INVALID_COMMAND, commandTypeAsString));
    }
}