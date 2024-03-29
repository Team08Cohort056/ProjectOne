package com.project.oop.tasksmanagement.commands.addcreatecommands;

import com.project.oop.tasksmanagement.commands.contracts.BaseCommand;
import com.project.oop.tasksmanagement.core.contracts.TaskManagementRepository;
import com.project.oop.tasksmanagement.models.contracts.Member;
import com.project.oop.tasksmanagement.models.contracts.Team;
import com.project.oop.tasksmanagement.utils.ValidationHelpers;

import java.util.List;

public class AddMemberToTeamCommand implements BaseCommand {
    private static final String MEMBER_ADDED_TO_TEAM = "%s member added to team %s.";

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

    private final TaskManagementRepository taskManagementRepository;

    public AddMemberToTeamCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }

    @Override
    public String execute(List<String> arguments) {
        ValidationHelpers.validateArgumentsCount(arguments, EXPECTED_NUMBER_OF_ARGUMENTS);

        String memberName = arguments.get(0);
        String teamName = arguments.get(1);


        Team team = taskManagementRepository.findTeamByName(teamName);
        Member member = taskManagementRepository.findMemberByName(memberName);
        team.addMember(member);

        return String.format(MEMBER_ADDED_TO_TEAM, memberName, teamName);
    }
}
