package com.project.oop.tasksmanagement.commands;

import com.project.oop.tasksmanagement.commands.contracts.BaseCommand;
import com.project.oop.tasksmanagement.core.contracts.TaskManagementRepository;
import com.project.oop.tasksmanagement.utils.ValidationHelpers;

import java.util.List;

public class ShowTeamDevelopers implements BaseCommand {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

    private final TaskManagementRepository repository;

    public ShowTeamDevelopers(TaskManagementRepository taskManagementRepository) {
        this.repository = taskManagementRepository;
    }

    @Override
    public String execute(List<String> commands) {
        ValidationHelpers.validateArgumentsCount(commands,EXPECTED_NUMBER_OF_ARGUMENTS);
        String targetTeam = commands.get(0);
        return repository.findTeamByName(targetTeam).printTeamDevelopers();
    }

}
