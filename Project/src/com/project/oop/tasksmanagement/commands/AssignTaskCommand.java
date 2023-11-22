package com.project.oop.tasksmanagement.commands;

import com.project.oop.tasksmanagement.commands.contracts.BaseCommand;
import com.project.oop.tasksmanagement.core.contracts.TaskManagementRepository;
import com.project.oop.tasksmanagement.models.contracts.AssignabelTask;
import com.project.oop.tasksmanagement.models.enums.TaskType;
import com.project.oop.tasksmanagement.utils.ParsingHelpers;
import com.project.oop.tasksmanagement.utils.ValidationHelpers;

import java.util.List;

public class AssignTaskCommand implements BaseCommand {
    private static final int NUMBER_OF_PARAMETERS = 2;
    private static final String INVALID_ID_ERR = "Please enter a valid ID. Should be integer";
    private static final String TASK_NOT_ASSIGNABLE_ERR = "Only bug and story tasks can be assigned.";
    private static final String TASK_ASSIGNED_MESSAGE = "Task with ID %d has been assigned to %s";
    private final TaskManagementRepository taskManagementRepository;

    public AssignTaskCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }

    @Override
    public String execute(List<String> commands) {
        ValidationHelpers.validateArgumentsCount(commands,NUMBER_OF_PARAMETERS);
        String memberName = commands.get(0);
        int id = ParsingHelpers.tryParseInt(commands.get(1), INVALID_ID_ERR);
        return assignTask(memberName,id);
    }
    private String assignTask(String memberName, int id){
        if (taskIsAssignable(id)){
            AssignabelTask assignableTask = taskManagementRepository.findAssignableTaskById(id);
            taskManagementRepository.findMemberByName(memberName).addTask(assignableTask);
            assignableTask.assignTaskTo(memberName);
            //TODO put the task in the list with assigned tasks
        }
        return TASK_ASSIGNED_MESSAGE.formatted(id,memberName);
    }

    private boolean taskIsAssignable(int id){
        if (taskManagementRepository.findTaskById(id).getTaskType().equals(TaskType.FEEDBACK)){
            throw new IllegalArgumentException(TASK_NOT_ASSIGNABLE_ERR);
        }
        return true;
    }
}
