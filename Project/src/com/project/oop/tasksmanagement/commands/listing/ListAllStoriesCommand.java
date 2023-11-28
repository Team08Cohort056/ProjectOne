package com.project.oop.tasksmanagement.commands.listing;

import com.project.oop.tasksmanagement.commands.contracts.BaseCommand;
import com.project.oop.tasksmanagement.core.contracts.TaskManagementRepository;
import com.project.oop.tasksmanagement.models.contracts.Story;
import com.project.oop.tasksmanagement.models.enums.Status;
import com.project.oop.tasksmanagement.utils.ListingHelpers;
import com.project.oop.tasksmanagement.utils.ParsingHelpers;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ListAllStoriesCommand implements BaseCommand {
    private static final String NO_STORIES_WITH_THAT_STATUS = "There are no stories with status %s";
    private static final String NO_STORIES_WITH_THAT_CRITERIA = "There are no stories with that criteria";
    private static final String NO_STORIES_WITH_THAT_ASSIGNEE = "There are no stories for that assignee %s";
    private final TaskManagementRepository taskManagementRepository;

    public ListAllStoriesCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }

    @Override
    public String execute(List<String> commands) {
        if (commands.get(0).equals("status")) {
            Status status = ParsingHelpers.tryParseEnum(commands.get(1), Status.class);
            String sortType = commands.get(2);
            return listStoriesByStatus(status, sortType);
        }
        if (commands.get(0).equals("assignee")) {
            String assignee = commands.get(1);
            String sortType = commands.get(2);
            return listStoriesByAssignee(assignee, sortType);
        } else {
            Status status = ParsingHelpers.tryParseEnum(commands.get(0), Status.class);
            String assignee = commands.get(1);
            String sortType = commands.get(2);
            return listStoriesByStatusAndAssignee(status, assignee, sortType);
        }
    }

    private String listStoriesByStatus(Status status, String sortType) {
        return "--STORIES--\n" + findStoryByFilter(status, sortType);
    }

    private String listStoriesByStatusAndAssignee(Status status, String assignee, String sortType) {
        return "--STORIES--\n" + findStoryByFilter(status, assignee, sortType);
    }

    private String listStoriesByAssignee(String assignee, String sortType) {
        return "--STORIES--\n" + findStoryByFilter(assignee, sortType);
    }

    public String findStoryByFilter(Status status, String sortType) {
        taskManagementRepository.getStories()
                .stream()
                .filter(u -> u.getStatus().equals(status))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(String.format(NO_STORIES_WITH_THAT_STATUS, status)));
        return taskManagementRepository.getStories()
                .stream()
                .sorted(sortedByType(sortType))
                .filter(u -> u.getStatus().equals(status))
                .map(Object::toString)
                .collect(Collectors.joining(System.lineSeparator()));
    }

    public String findStoryByFilter(Status status, String assignee, String sortType) {
        taskManagementRepository.getStories()
                .stream()
                .filter(u -> u.getStatus().equals(status))
                .filter(u -> u.getAssignee().equalsIgnoreCase(assignee))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(NO_STORIES_WITH_THAT_CRITERIA));
        return taskManagementRepository.getStories()
                .stream()
                .sorted(sortedByType(sortType))
                .filter(u -> u.getStatus().equals(status))
                .filter(u -> u.getAssignee().equalsIgnoreCase(assignee))
                .map(Object::toString)
                .collect(Collectors.joining(System.lineSeparator()));
    }

    public String findStoryByFilter(String assignee, String sortType) {
        taskManagementRepository.getStories()
                .stream()
                .filter(u -> u.getAssignee().equalsIgnoreCase(assignee))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(String.format(NO_STORIES_WITH_THAT_ASSIGNEE, assignee)));
        return taskManagementRepository.getStories()
                .stream()
                .sorted(sortedByType(sortType))
                .filter(u -> u.getAssignee().equalsIgnoreCase(assignee))
                .map(Object::toString)
                .collect(Collectors.joining(System.lineSeparator()));
    }

    public Comparator<? super Story> sortedByType(String sortType) {
        if (sortType.equals("title")) {
            return ListingHelpers.comparatorByTitle;
        }
        if (sortType.equals("priority")) {
            return ListingHelpers.comparatorByPriority;
        }
        return ListingHelpers.comparatorBySize;
    }
}

