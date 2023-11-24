package com.project.oop.tasksmanagement.commands.listing;

import com.project.oop.tasksmanagement.commands.contracts.BaseCommand;
import com.project.oop.tasksmanagement.core.contracts.TaskManagementRepository;
import com.project.oop.tasksmanagement.models.contracts.Feedback;
import com.project.oop.tasksmanagement.models.contracts.Story;
import com.project.oop.tasksmanagement.models.contracts.Task;
import com.project.oop.tasksmanagement.models.enums.Status;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ListAllFeedbacksCommand implements BaseCommand {
    private final TaskManagementRepository repository;
    private final Comparator<Feedback> comparator = Comparator
            .comparing(Feedback::getTitle)
            .thenComparingInt(Feedback::getRating);

    public ListAllFeedbacksCommand(TaskManagementRepository taskManagementRepository) {
        this.repository = taskManagementRepository;
    }
    public String execute(List<String> commands) {
        if (commands.size()==0 ){
            return listAllFeedbacksSorted();
        }
        if (commands.size()==1 && commands.get(0).equalsIgnoreCase("FILTERBYSTATUS")){
            return "Please enter a valid status to filter";
        }
        else if (commands.size()==2 && commands.get(0).equalsIgnoreCase("FILTERBYSTATUS")){
            Status status = Status.valueOf(commands.get(1));
            return filterAllFeedbacksByStatus(status);
        }
        else
        throw new IllegalArgumentException("Invalid parameters for command LISTALLFEEDBACKS");
    }
    public String listAllFeedbacksSorted(){
        return repository.getFeedbacks()
                .stream()
                .sorted(comparator)
                .map(Object::toString)
                .collect(Collectors.joining(System.lineSeparator()));
    }
    public String filterAllFeedbacksByStatus(Status status){
        return repository.getFeedbacks()
                .stream()
                .sorted(comparator)
                .filter(u -> u.getStatus().equals(status))
                .map(Object::toString)
                .collect(Collectors.joining(System.lineSeparator()));
    }

}
