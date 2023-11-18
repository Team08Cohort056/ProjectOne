package com.project.oop.tasksmanagement.commands;

import com.project.oop.tasksmanagement.commands.contracts.BaseCommand;
import com.project.oop.tasksmanagement.core.contracts.TaskManagementRepository;
import com.project.oop.tasksmanagement.utils.ValidationHelpers;

import java.util.List;

public class ShowAllCommand implements BaseCommand {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    public static final String INVALID_COMMAND = "Invalid Type.";

    private final TaskManagementRepository repository;

    public ShowAllCommand(TaskManagementRepository taskManagementRepository) {
        this.repository = taskManagementRepository;
    }

    @Override
    public String execute(List<String> commands) {
        ValidationHelpers.validateArgumentsCount(commands,EXPECTED_NUMBER_OF_ARGUMENTS);
        String collectionToPrint = commands.get(0).toUpperCase();
        return printAll(collectionToPrint);
    }

    public String printAll(String collectionToPrint){
        switch (collectionToPrint){
            case "TEAMS":
                return repository.printTeams();
            case "DEVELOPERS":
                return repository.printDevelopers();
            case "BOARDS":
                return repository.printBoards();
            case "TASKS":
                return repository.printTasks();
            default:
                return INVALID_COMMAND;
        }
    }


}
