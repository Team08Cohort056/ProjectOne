package com.project.oop.tasksmanagement.commands;

import com.project.oop.tasksmanagement.commands.contracts.BaseCommand;
import com.project.oop.tasksmanagement.core.contracts.TaskManagementRepository;
import com.project.oop.tasksmanagement.core.exceptions.InvalidUserInputException;
import com.project.oop.tasksmanagement.models.MemberImpl;
import com.project.oop.tasksmanagement.utils.ValidationHelpers;

import java.util.List;

public class CreateMemberCommand implements BaseCommand {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    private final static String USERNAME_ALREADY_EXIST = "Username %s is already taken. Please choose another username!";
    public final static String MEMBER_CREATED_SUCCESS = "%s successfully joins our company!";

    private final TaskManagementRepository taskManagementRepository;


    public CreateMemberCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }


    @Override
    public String execute(List<String> commands) {
        ValidationHelpers.validateArgumentsCount(commands, EXPECTED_NUMBER_OF_ARGUMENTS);
        String memberName = commands.get(0);
        return createMember(memberName);
    }

    private String createMember(String name) {
        if (taskManagementRepository.memberExists(name)) {
            throw new InvalidUserInputException(String.format(USERNAME_ALREADY_EXIST, name));
        }
        taskManagementRepository.addMember(new MemberImpl(name));
        return String.format(MEMBER_CREATED_SUCCESS, name);
    }
}
