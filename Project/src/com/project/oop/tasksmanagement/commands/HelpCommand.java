package com.project.oop.tasksmanagement.commands;

import com.project.oop.tasksmanagement.commands.contracts.BaseCommand;
import com.project.oop.tasksmanagement.commands.enums.CommandType;
import com.project.oop.tasksmanagement.commands.enums.CommandTypeHelper;
import com.project.oop.tasksmanagement.core.contracts.TaskManagementRepository;
import com.project.oop.tasksmanagement.utils.ValidationHelpers;

import java.util.Arrays;
import java.util.List;

public class HelpCommand implements BaseCommand {
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    public static final String DEFAULT_HELP_MESSAGE = String.format(
            "help commandlist - Shows a list of all available commands.%n" +
                    "help {COMMANDNAME} - Shows instructions on how to use the specified command.%n" +
                    "Exit - Terminates the program. ");
    public static final String COMMANDLIST_MESSAGE = "Below you can see all available commands. You can enter help and follow it up with the command you need information on.";
    public static final String COMMAND_NOT_FOUND_ERR = "Command %s not found";
    private final TaskManagementRepository repository;

    public HelpCommand(TaskManagementRepository taskManagementRepository) {
        this.repository = taskManagementRepository;

    }

    @Override
    public String execute(List<String> commands) {
        if (commands.size()==0){return DEFAULT_HELP_MESSAGE;}
        ValidationHelpers.validateArgumentsCount(commands,EXPECTED_NUMBER_OF_ARGUMENTS);
        String helpWithCommand = commands.get(0).toUpperCase();
        return helper(helpWithCommand);
    }
    public String helper(String helpWithCommand){
        StringBuilder result = new StringBuilder();
        int counter = 1;
        List<CommandType> commandList = List.of(CommandType.values());
        switch (helpWithCommand){
            case  "COMMANDLIST":
                result.append(COMMANDLIST_MESSAGE).append(System.lineSeparator());
                for (CommandType type: commandList ) {
                    result.append(counter).append(".").append(type.toString().toLowerCase()).append(System.lineSeparator());
                    counter++;
                }
            case "ADDBOARD":
                result.append(CommandTypeHelper.ADDBOARD);
            case "ADDTASK":
                result.append(CommandTypeHelper.ADDTASK);
            case "ADDMEMBERTOTEAM":
                result.append(CommandTypeHelper.ADDMEMBERTOTEAM);
            case "ASSIGNTASK":
                result.append(CommandTypeHelper.ASSIGNTASK);
            case "UNASSIGNTASK":
                result.append(CommandTypeHelper.UNASSIGNTASK);
            case "ADDCOMMENT":
                result.append(CommandTypeHelper.ADDCOMMENT);
            case "REMOVECOMMENT":
                result.append(CommandTypeHelper.REMOVECOMMENT);
            case "CREATEMEMBER":
                result.append(CommandTypeHelper.CREATEMEMBER);
            case "CREATETEAM":
                result.append(CommandTypeHelper.CREATETEAM);
            case "CHANGETASK":
                result.append(CommandTypeHelper.CHANGETASK);
            case "SHOWALL":
                result.append(CommandTypeHelper.SHOWALL);
            case "SHOWACTIVITY":
                result.append(CommandTypeHelper.SHOWACTIVITY);
            case "SHOWTEAMMEMBERS":
                result.append(CommandTypeHelper.SHOWTEAMMEMBERS);
            case "SHOWTEAMBOARDS":
                result.append(CommandTypeHelper.SHOWTEAMBOARDS);
            case "LISTALL":
                result.append(CommandTypeHelper.LISTALL);
            case "LISTASSIGNEDTASKS":
                result.append(CommandTypeHelper.LISTASSIGNEDTASKS);
            case "HELP":
                result.append(CommandTypeHelper.HELP);
            default:
                result.append(String.format(COMMAND_NOT_FOUND_ERR,helpWithCommand));

        }
        return result.toString();
    }



}
