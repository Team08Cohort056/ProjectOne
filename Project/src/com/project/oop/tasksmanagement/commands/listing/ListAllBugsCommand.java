package com.project.oop.tasksmanagement.commands.listing;

import com.project.oop.tasksmanagement.commands.contracts.BaseCommand;
import com.project.oop.tasksmanagement.core.contracts.TaskManagementRepository;
import com.project.oop.tasksmanagement.models.contracts.Bug;
import com.project.oop.tasksmanagement.models.enums.Status;
import com.project.oop.tasksmanagement.utils.ParsingHelpers;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ListAllBugsCommand implements BaseCommand {
    private static final String INVALID_NUMBER_OF_ARGUMENTS_ERR = "Invalid number of arguments.Expects 2(if only one filter is applied) or 4(if two filters are applied)arguments. Received %d.";
    private static final int MIN_ARGUMENTS = 2;
    private static final String NO_ASSIGNED_BUGS_WITH_STATUS_ERR = "There is no assigned bug tasks with status %s yet.";
    private static final String NO_ASSIGNED_BUGS_WITH_ASSIGNEE_ERR = "There is no assigned bug tasks to %s yet.";
    private static final String NO_ASSIGNED_BUGS_WITH_STATUS_ASSIGNEE_ERR = "There is no assigned bug tasks with status %s to %s yet.";
    private static final String STATUS = "status";
    private static final String ASSIGNEE = "assignee";
    private static final Comparator<Bug> BUG_COMPARATOR = Comparator.comparing(Bug::getTitle)
            .thenComparing(Bug::getPriority).thenComparing(Bug::getSeverity);
    private final TaskManagementRepository taskManagementRepository;



    public ListAllBugsCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }

    @Override
    public String execute(List<String> commands) {
        if (commands.size()< MIN_ARGUMENTS){
            throw  new IllegalArgumentException(INVALID_NUMBER_OF_ARGUMENTS_ERR.formatted(commands.size()));
        }
        if (commands.size() == 2 && commands.get(0).equalsIgnoreCase(STATUS)){
            Status status = ParsingHelpers.tryParseEnum(commands.get(1),Status.class);
            return listAllBugsFilterByStatus(status);
        } else if (commands.size() == 2 && commands.get(0).equalsIgnoreCase(ASSIGNEE)){
            String assignee = commands.get(1);
            return listAllBugsFilterByAssignee(assignee);
        } else if (commands.size() == 4) {
            Status status = ParsingHelpers.tryParseEnum(commands.get(1),Status.class);
            String assignee = commands.get(3);
            return listAllBugsFilterByStatusAndAssignee(status,assignee);
        }
        throw new IllegalArgumentException(INVALID_NUMBER_OF_ARGUMENTS_ERR.formatted(commands.size()));

    }

    private String listAllBugsFilterByStatus(Status status){
        if (isListEmpty(status)){
            return NO_ASSIGNED_BUGS_WITH_STATUS_ERR.formatted(status);
        }
        return taskManagementRepository.getBugs().stream().filter(task -> task.getStatus().equals(status))
                .sorted(BUG_COMPARATOR).map(Object::toString)
                .collect(Collectors.joining(System.lineSeparator()));

    }

    private String listAllBugsFilterByAssignee(String assignee){
        if (isListEmpty(assignee)){
            return NO_ASSIGNED_BUGS_WITH_ASSIGNEE_ERR.formatted(assignee);
        }
        return    taskManagementRepository.getBugs().stream().filter(task -> task.getAssignee().equals(assignee))
                .sorted(BUG_COMPARATOR).map(Object::toString)
                .collect(Collectors.joining(System.lineSeparator()));

    }

    private String listAllBugsFilterByStatusAndAssignee(Status status, String assignee){
        if (isListEmpty(status,assignee)){
            return NO_ASSIGNED_BUGS_WITH_STATUS_ASSIGNEE_ERR.formatted(status,assignee);
        }
        return  taskManagementRepository.getBugs().stream().filter(task -> task.getStatus().equals(status))
                .filter(task -> task.getAssignee().equals(assignee))
                .sorted(BUG_COMPARATOR).map(Object::toString)
                .collect(Collectors.joining(System.lineSeparator()));
    }

    private boolean isListEmpty(Status status, String assignee){
        return isListEmpty(status) || isListEmpty(assignee);
    }

    private boolean isListEmpty(Status status){
        return taskManagementRepository.getBugs().stream().noneMatch(task -> task.getStatus().equals(status));
    }

    private boolean isListEmpty(String assignee){
        return taskManagementRepository.getBugs().stream().noneMatch(task -> task.getAssignee().equals(assignee));
    }
}
