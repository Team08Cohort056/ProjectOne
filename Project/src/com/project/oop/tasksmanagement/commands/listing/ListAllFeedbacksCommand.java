package com.project.oop.tasksmanagement.commands.listing;

import com.project.oop.tasksmanagement.commands.contracts.BaseCommand;
import com.project.oop.tasksmanagement.core.contracts.TaskManagementRepository;
import com.project.oop.tasksmanagement.models.contracts.Feedback;
import com.project.oop.tasksmanagement.models.contracts.Story;
import com.project.oop.tasksmanagement.models.contracts.Task;
import com.project.oop.tasksmanagement.models.enums.Status;
import com.project.oop.tasksmanagement.utils.ListingHelpers;
import com.project.oop.tasksmanagement.utils.ParsingHelpers;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ListAllFeedbacksCommand implements BaseCommand {
    private static final String STATUS = "STATUS";
    private static final String INVALID_PARAMETERS_ERR = "Invalid parameters for command LISTALLFEEDBACKS.";
    private static final String NO_STATUS_ENTERED_ERR = "Please enter a valid status to filter.";
    private static final String NO_ASSIGNED_FEEDBACKS_WITH_STATUS_ERR = "There is no assigned feedback tasks with status %s yet.";

    private final TaskManagementRepository repository;

    public ListAllFeedbacksCommand(TaskManagementRepository taskManagementRepository) {
        this.repository = taskManagementRepository;
    }
    public String execute(List<String> commands) {
        if (commands.size()==0 ){
            return listAllFeedbacksSorted();
        }
        if (commands.size()==1 && commands.get(0).equalsIgnoreCase(STATUS)){
            return NO_STATUS_ENTERED_ERR;
        }
        else if (commands.size()==2 && commands.get(0).equalsIgnoreCase(STATUS)){
            Status status = ParsingHelpers.tryParseEnum(commands.get(1).toUpperCase(),Status.class);
            return filterAllFeedbacksByStatus(status);
        }
        throw new IllegalArgumentException(INVALID_PARAMETERS_ERR);
    }
    public String listAllFeedbacksSorted(){
        return repository.getFeedbacks()
                .stream()
                .sorted(ListingHelpers.getfeedbackComparator())
                .map(Object::toString)
                .collect(Collectors.joining(System.lineSeparator()));
    }
    public String filterAllFeedbacksByStatus(Status status){
        //Validates if filtered list is empty and returns an error if it is.
        repository.getFeedbacks()
                .stream()
                .sorted(ListingHelpers.getfeedbackComparator())
                .filter(u -> u.getStatus().equals(status))
                .findAny()
                .orElseThrow(()->new IllegalArgumentException(String.format(NO_ASSIGNED_FEEDBACKS_WITH_STATUS_ERR,status)));


        return repository.getFeedbacks()
                .stream()
                .sorted(ListingHelpers.getfeedbackComparator())
                .filter(u -> u.getStatus().equals(status))
                .map(Object::toString)
                .collect(Collectors.joining(System.lineSeparator()));
    }

}
