package com.project.oop.tasksmanagement.commands.listing;

import com.project.oop.tasksmanagement.commands.contracts.BaseCommand;
import com.project.oop.tasksmanagement.core.contracts.TaskManagementRepository;

import java.util.List;

public class ListAllBugsCommand implements BaseCommand {

    private static final String INVALID_NUMBER_OF_ARGUMENTS_ERR = "Invalid number of arguments.Expects 2(if only one filter is applied) or 3(if two filters are applied).Received %d.";

    private final TaskManagementRepository taskManagementRepository;

    public ListAllBugsCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }

    @Override
    public String execute(List<String> commands) {
        if (commands.size() == 2){

        } else if (commands.size() == 3){

        }
        throw new IllegalArgumentException(INVALID_NUMBER_OF_ARGUMENTS_ERR.formatted(commands.size()));
    }
}
