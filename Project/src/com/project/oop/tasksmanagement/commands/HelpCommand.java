package com.project.oop.tasksmanagement.commands;

import com.project.oop.tasksmanagement.commands.contracts.BaseCommand;
import com.project.oop.tasksmanagement.commands.enums.CommandType;
import com.project.oop.tasksmanagement.commands.enums.CommandTypeHelper;
import com.project.oop.tasksmanagement.core.contracts.TaskManagementRepository;
import com.project.oop.tasksmanagement.utils.ValidationHelpers;

import java.util.List;

public class HelpCommand implements BaseCommand {
    private static final int MAXIMUM_NUMBER_OF_ARGUMENTS = 1;
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
        ValidationHelpers.validateArgumentsCount(commands, MAXIMUM_NUMBER_OF_ARGUMENTS);
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
                break;
            case "ADDBOARD":
                result.append(CommandTypeHelper.ADDBOARD);
                break;
            case "ADDTASK":
                result.append(CommandTypeHelper.ADDTASK);
                break;
            case "ADDMEMBERTOTEAM":
                result.append(CommandTypeHelper.ADDMEMBERTOTEAM);
                break;
            case "ADDBUGREPRODUCESTEPS":
                result.append(CommandTypeHelper.ADDBUGREPRODUCESTEPS);
                break;
            case "ASSIGNTASK":
                result.append(CommandTypeHelper.ASSIGNTASK);
                break;
            case "UNASSIGNTASK":
                result.append(CommandTypeHelper.UNASSIGNTASK);
                break;
            case "ADDCOMMENT":
                result.append(CommandTypeHelper.ADDCOMMENT);
                break;
            case "REMOVECOMMENT":
                result.append(CommandTypeHelper.REMOVECOMMENT);
                break;
            case "CREATEMEMBER":
                result.append(CommandTypeHelper.CREATEMEMBER);
                break;
            case "CREATETEAM":
                result.append(CommandTypeHelper.CREATETEAM);
                break;
            case "CHANGETASK":
                result.append(CommandTypeHelper.CHANGETASK);
                break;
            case "SHOWALL":
                result.append(CommandTypeHelper.SHOWALL);
                break;
            case "SHOWACTIVITY":
                result.append(CommandTypeHelper.SHOWACTIVITY);
                break;
            case "SHOWTEAMMEMBERS":
                result.append(CommandTypeHelper.SHOWTEAMMEMBERS);
                break;
            case "SHOWTEAMBOARDS":
                result.append(CommandTypeHelper.SHOWTEAMBOARDS);
                break;
            case "LISTALL":
                result.append(CommandTypeHelper.LISTALL);
                break;
            case "LISTASSIGNEDTASKS":
                result.append(CommandTypeHelper.LISTASSIGNEDTASKS);
                break;
            case "HELP":
                result.append(CommandTypeHelper.HELP);
                break;
            default:
                throw new IllegalArgumentException(String.format(COMMAND_NOT_FOUND_ERR,helpWithCommand));
        }
        return result.toString();
    }



}
