package com.project.oop.tasksmanagement.core.commands;

import com.project.oop.tasksmanagement.core.contracts.BaseCommand;
import com.project.oop.tasksmanagement.core.contracts.TaskManagementRepository;
import com.project.oop.tasksmanagement.models.TeamImpl;
import com.project.oop.tasksmanagement.models.contracts.Team;
import com.project.oop.tasksmanagement.utils.ValidationHelpers;

import java.util.List;

public class CreateTeamCommand implements BaseCommand {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    private TaskManagementRepository repository;

    public CreateTeamCommand(TaskManagementRepository repository) {
        this.repository = repository;
    }

    @Override
    public String execute(List<String> commands) {
        ValidationHelpers.validateArgumentsCount(commands,EXPECTED_NUMBER_OF_ARGUMENTS);
        return createTeam(commands.get(0));
    }

    private String createTeam(String teamName){
        //Validation for duplicate needed.
        //TODO
        Team team = new TeamImpl(teamName);
        repository.createTeam(teamName);

        return String.format("Team %s created",teamName);
    }

}
