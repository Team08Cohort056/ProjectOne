package com.project.oop.tasksmanagement.commands.listing;

import com.project.oop.tasksmanagement.commands.contracts.BaseCommand;
import com.project.oop.tasksmanagement.core.contracts.TaskManagementRepository;
import com.project.oop.tasksmanagement.models.contracts.Task;
import com.project.oop.tasksmanagement.models.enums.Status;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ListAllTasksCommand implements BaseCommand {
    private static final String INVALID_PARAMETERS_ERR = "Invalid parameters for command LISTALLTASKS.";
    private static final String TITLE = "TITLE";
    private static final String NO_ASSIGNED_TASKS_WITH_STATUS_ERR = "There are no tasks with title %s yet.";
    private static final String NO_TASKS_FOUND_ERR = "No tasks found";
    private static final String NO_TITLE_ENTERED_ERR = "Please enter a valid title to filter.";


    private final TaskManagementRepository repository;

    public ListAllTasksCommand(TaskManagementRepository taskManagementRepository) {
        this.repository = taskManagementRepository;
    }

    @Override
    public String execute(List<String> commands) {
        if (commands.size()==0 ){
            return listAllTasksSorted();
        }
        if (commands.size()==1 && commands.get(0).equalsIgnoreCase(TITLE)){
            return NO_TITLE_ENTERED_ERR;
        }
        else if (commands.size()==2 && commands.get(0).equalsIgnoreCase(TITLE)){
            String targetTitleName = commands.get(1);
            return filterAllTasksByTitle(targetTitleName);
        }
        throw new IllegalArgumentException(INVALID_PARAMETERS_ERR);
    }
    public String listAllTasksSorted(){
        if (repository.getAllTasks().isEmpty()){
            return NO_TASKS_FOUND_ERR;
        }
        return repository.getAllTasks()
                .stream()
                .sorted(Comparator.comparing(Task::getTitle))
                .map(Object::toString)
                .collect(Collectors.joining(System.lineSeparator()));
    }
    public String filterAllTasksByTitle(String targetTitleName){
        if (isListEmpty(targetTitleName)){
            return NO_ASSIGNED_TASKS_WITH_STATUS_ERR.formatted(targetTitleName);
        }
        return repository.getAllTasks()
                .stream()
                .sorted(Comparator.comparing(Task::getTitle))
                .filter(t->t.getTitle().equalsIgnoreCase(targetTitleName))
                .map(Object::toString)
                .collect(Collectors.joining(System.lineSeparator()));
    }
    private boolean isListEmpty(String title){
        return repository.getAllTasks().stream().noneMatch(task -> task.getTitle().equals(title));
    }
}
