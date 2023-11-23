package com.project.oop.tasksmanagement.commands.listing;

import com.project.oop.tasksmanagement.commands.contracts.BaseCommand;
import com.project.oop.tasksmanagement.core.contracts.TaskManagementRepository;
import com.project.oop.tasksmanagement.models.contracts.Task;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ListAllTasksCommand implements BaseCommand {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    public static final String INVALID_COMMAND = "Invalid Type.";

    private final TaskManagementRepository repository;

    public ListAllTasksCommand(TaskManagementRepository taskManagementRepository) {
        this.repository = taskManagementRepository;
    }

    @Override
    public String execute(List<String> commands) {
        if (commands.size()==0 ){
            return listAllTasksSorted();
        }
        else if (commands.size()==2 && commands.get(0).equalsIgnoreCase("FILTERBYTITLE")){
            String targetTitleName = commands.get(1);
            return filterAllTasksByTitle(targetTitleName);
        }
        throw new IllegalArgumentException();
    }
    public String listAllTasksSorted(){
        return repository.getAllTasks()
                .stream()
                .sorted(Comparator.comparing(Task::getTitle))
                .map(Object::toString)
                .collect(Collectors.joining(System.lineSeparator()));
    }
    public String filterAllTasksByTitle(String targetTitleName){
        return repository.getAllTasks()
                .stream()
                .sorted(Comparator.comparing(Task::getTitle))
                .filter(t->t.getTitle().equals(targetTitleName))
                .map(Object::toString)
                .collect(Collectors.joining(System.lineSeparator()));
    }
}
