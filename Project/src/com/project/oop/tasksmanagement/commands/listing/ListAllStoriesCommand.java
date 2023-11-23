package com.project.oop.tasksmanagement.commands.listing;

import com.project.oop.tasksmanagement.commands.contracts.BaseCommand;
import com.project.oop.tasksmanagement.core.contracts.TaskManagementRepository;
import com.project.oop.tasksmanagement.models.contracts.Story;
import com.project.oop.tasksmanagement.models.enums.Status;
import com.project.oop.tasksmanagement.utils.ParsingHelpers;

import java.util.List;

public class ListAllStoriesCommand implements BaseCommand {
    private final TaskManagementRepository taskManagementRepository;

    public ListAllStoriesCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }

    @Override
    public String execute(List<String> commands) {
        if (commands.get(0).equals("status")) {
            Status status = ParsingHelpers.tryParseEnum(commands.get(1),Status.class);
            return listStoriesByStatus(status);
        }
        if (commands.get(0).equals("assignee")) {
            String assignee = commands.get(1);
            return listStoriesByAssignee(assignee);
        } else {
            Status status = ParsingHelpers.tryParseEnum(commands.get(1),Status.class);
            String assignee = commands.get(1);
            return listStoriesByStatusAndAssignee(status, assignee);
        }
    }

    private String listStoriesByStatus(Status status) {
        Story story = taskManagementRepository.findStoryByFilter(status);

        return String.format("--STORIES--\n" + story.toString());
    }

    private String listStoriesByStatusAndAssignee(Status status, String assignee) {
        Story story = taskManagementRepository.findStoryByFilter(status, assignee);

        return String.format("--STORIES--\n" + story.toString());
    }

    private String listStoriesByAssignee(String assignee) {
        Story story = taskManagementRepository.findStoryByFilter(assignee);

        return String.format("--STORIES--\n" + story.toString());
    }
}

