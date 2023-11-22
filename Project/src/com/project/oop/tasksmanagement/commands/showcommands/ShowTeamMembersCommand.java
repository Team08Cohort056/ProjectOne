package com.project.oop.tasksmanagement.commands.showcommands;

import com.project.oop.tasksmanagement.commands.contracts.BaseCommand;
import com.project.oop.tasksmanagement.core.contracts.TaskManagementRepository;
import com.project.oop.tasksmanagement.utils.ValidationHelpers;

import java.util.List;

public class ShowTeamMembersCommand implements BaseCommand {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

    private final TaskManagementRepository repository;

    public ShowTeamMembersCommand(TaskManagementRepository taskManagementRepository) {
        this.repository = taskManagementRepository;
    }

    @Override
    public String execute(List<String> commands) {
        ValidationHelpers.validateArgumentsCount(commands,EXPECTED_NUMBER_OF_ARGUMENTS);
        String targetTeam = commands.get(0);
        return repository.findTeamByName(targetTeam).printTeamMembers();
    }

}
