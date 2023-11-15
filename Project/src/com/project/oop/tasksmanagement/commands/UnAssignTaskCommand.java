package com.project.oop.tasksmanagement.commands;

import com.project.oop.tasksmanagement.commands.contracts.BaseCommand;
import com.project.oop.tasksmanagement.core.contracts.TaskManagementRepository;
import com.project.oop.tasksmanagement.models.contracts.Developer;
import com.project.oop.tasksmanagement.models.contracts.Task;
import com.project.oop.tasksmanagement.utils.ParsingHelpers;
import com.project.oop.tasksmanagement.utils.ValidationHelpers;

import java.util.List;

public class UnAssignTaskCommand implements BaseCommand {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    public static final String NO_TASK_ON_THIS_INDEX_ERR = "There is no Task on this index!";
    public static final String INVALID_ID_ERR = "Please enter a valid ID.";
    public static final String TASK_REMOVED_SUCCESSFULLY = "Task with ID#%d unassigned successfully from %s.";
    private final TaskManagementRepository repository;

    public UnAssignTaskCommand(TaskManagementRepository taskManagementRepository) {
        this.repository = taskManagementRepository;
    }

    @Override
    public String execute(List<String> commands) {
        ValidationHelpers.validateArgumentsCount(commands,EXPECTED_NUMBER_OF_ARGUMENTS);
        String developerName = commands.get(0);
        int taskId = ParsingHelpers.tryParseInt(commands.get(1), INVALID_ID_ERR);
        return unAssignTask(developerName,taskId);
    }
    public String unAssignTask(String developerName, int taskId){
        Developer developer = repository.findMemberByName(developerName);
        ValidationHelpers.validateIntRange(taskId,0,developer.getTasks().size()-1, NO_TASK_ON_THIS_INDEX_ERR);
        Task task = developer.getTasks().get(taskId);
        repository.findMemberByName(developerName).removeTask(task);
        return String.format(TASK_REMOVED_SUCCESSFULLY,taskId,developerName);
    }



}