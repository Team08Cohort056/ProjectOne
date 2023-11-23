package com.project.oop.tasksmanagement.commands.listing;

import com.project.oop.tasksmanagement.commands.contracts.BaseCommand;
import com.project.oop.tasksmanagement.core.contracts.TaskManagementRepository;
import com.project.oop.tasksmanagement.models.contracts.Task;
import com.project.oop.tasksmanagement.models.enums.Status;
import com.project.oop.tasksmanagement.utils.ParsingHelpers;

import java.util.Comparator;
import java.util.List;

public class ListAllAssignedTasksCommand implements BaseCommand {
    private static final String INVALID_NUMBER_OF_ARGUMENTS_ERR = "Invalid number of arguments.Expects 2(if only one filter is applied) or 4(if two filters are applied)arguments. Received %d.";
    private static final int MIN_ARGUMENTS = 2;
    private static final String NO_ASSIGNED_TASKS_ERR = "There is no assigned tasks yet.";
    private static final String STATUS = "status";
    private static final String ASSIGNEE = "assignee";
    private final TaskManagementRepository taskManagementRepository;



    public ListAllAssignedTasksCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }

    @Override
    public String execute(List<String> commands) {
        if (commands.size()< MIN_ARGUMENTS){
            throw new IllegalArgumentException(INVALID_NUMBER_OF_ARGUMENTS_ERR.formatted(commands.size()));
        }
        if (commands.size() == 2 && commands.get(0).equalsIgnoreCase(STATUS)){
            Status status = ParsingHelpers.tryParseEnum(commands.get(1),Status.class);
            listAllAssignedTasksFilterByStatus(status);
        } else if (commands.size() == 2 && commands.get(0).equalsIgnoreCase(ASSIGNEE)){
            String assignee = commands.get(1);
            listAllAssignedTasksFilterByAssignee(assignee);
        } else if (commands.size() == 4) {
            Status status = ParsingHelpers.tryParseEnum(commands.get(1),Status.class);
            String assignee = commands.get(3);
            listAllAssignedTasksFilterByStatusAndAssignee(status,assignee);
        }
        throw new IllegalArgumentException(INVALID_NUMBER_OF_ARGUMENTS_ERR.formatted(commands.size()));
    }

    private void listAllAssignedTasksFilterByStatus(Status status){
        if (taskManagementRepository.getAllAssignedTasks().isEmpty()){
            System.out.println(NO_ASSIGNED_TASKS_ERR);
        }else {
            taskManagementRepository.getAllAssignedTasks().stream().filter(task -> task.getStatus().equals(status))
                    .sorted(Comparator.comparing(Task::getTitle)).forEach(System.out::println);
        }
    }

    private void listAllAssignedTasksFilterByAssignee(String assignee){
        if (taskManagementRepository.getAllAssignedTasks().isEmpty()){
            System.out.println(NO_ASSIGNED_TASKS_ERR);
        }else {
            taskManagementRepository.getAllAssignedTasks().stream().filter(task -> task.getAssignee().equals(assignee))
                    .sorted(Comparator.comparing(Task::getTitle)).forEach(System.out::println);
        }
    }

    private void listAllAssignedTasksFilterByStatusAndAssignee(Status status, String assignee){
        if (taskManagementRepository.getAllAssignedTasks().isEmpty()){
            System.out.println(NO_ASSIGNED_TASKS_ERR);
        }else {
            taskManagementRepository.getAllAssignedTasks().stream().filter(task -> task.getStatus().equals(status))
                    .filter(task -> task.getAssignee().equals(assignee))
                    .sorted(Comparator.comparing(Task::getTitle)).forEach(System.out::println);
        }
    }
}
