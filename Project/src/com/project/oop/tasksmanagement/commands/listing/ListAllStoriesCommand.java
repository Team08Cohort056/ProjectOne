package com.project.oop.tasksmanagement.commands.listing;

import com.project.oop.tasksmanagement.commands.contracts.BaseCommand;
import com.project.oop.tasksmanagement.core.contracts.TaskManagementRepository;
import com.project.oop.tasksmanagement.models.contracts.Story;
import com.project.oop.tasksmanagement.models.enums.StoryStatus;
import com.project.oop.tasksmanagement.utils.ValidationHelpers;

import java.util.List;

public class ListAllStoriesCommand implements BaseCommand {
    private final TaskManagementRepository taskManagementRepository;

    public ListAllStoriesCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }

    @Override
    public String execute(List<String> commands) {
        if (commands.get(0).equals("status")) {
            StoryStatus status = StoryStatus.valueOf(commands.get(1));
            return listStoriesByStatus(status);
        }
        if (commands.get(0).equals("assignee")) {
            String assignee = commands.get(1);
            return listStoriesByAssignee(assignee);
        } else {
            StoryStatus status = StoryStatus.valueOf(commands.get(0));
            String assignee = commands.get(1);
            return listStoriesByStatusAndAssignee(status, assignee);
        }
    }

    private String listStoriesByStatus(StoryStatus status) {
        Story story = taskManagementRepository.findStoryByFilter(status);

        return String.format("--STORIES--\n" + story.toString());
    }

    private String listStoriesByStatusAndAssignee(StoryStatus status, String assignee) {
        Story story = taskManagementRepository.findStoryByFilter(status, assignee);

        return String.format("--STORIES--\n" + story.toString());
    }

    private String listStoriesByAssignee(String assignee) {
        Story story = taskManagementRepository.findStoryByFilter(assignee);

        return String.format("--STORIES--\n" + story.toString());
    }
}

