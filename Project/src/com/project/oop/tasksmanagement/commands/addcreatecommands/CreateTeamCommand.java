package com.project.oop.tasksmanagement.commands.addcreatecommands;

import com.project.oop.tasksmanagement.commands.contracts.BaseCommand;
import com.project.oop.tasksmanagement.core.contracts.TaskManagementRepository;
import com.project.oop.tasksmanagement.models.TeamImpl;
import com.project.oop.tasksmanagement.models.contracts.Team;
import com.project.oop.tasksmanagement.utils.ValidationHelpers;

import java.util.List;

public class CreateTeamCommand implements BaseCommand {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    public static final String TEAM_CREATED = "Team %s created.";
    public static final String TEAM_ALREADY_EXISTS_ERR = "Team %s already exists!";
    private final TaskManagementRepository repository;

    public CreateTeamCommand(TaskManagementRepository taskManagementRepository) {
        this.repository = taskManagementRepository;
    }

    @Override
    public String execute(List<String> commands) {
        ValidationHelpers.validateArgumentsCount(commands,EXPECTED_NUMBER_OF_ARGUMENTS);
        return createTeam(commands.get(0));
    }

    private String createTeam(String teamName){
        for (Team t : repository.getTeams()) {
            if (teamName.equals(t.getName())){
                throw new IllegalArgumentException(String.format(TEAM_ALREADY_EXISTS_ERR,teamName));
            }
        }
        Team team = new TeamImpl(teamName);
        repository.createTeam(teamName);
        repository.addTeam(team);
        return String.format(TEAM_CREATED,teamName);
    }

}
