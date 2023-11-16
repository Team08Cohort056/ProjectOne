package com.project.oop.tasksmanagement.models.tasks;

import com.project.oop.tasksmanagement.models.contracts.Commentable;
import com.project.oop.tasksmanagement.models.contracts.Story;
import com.project.oop.tasksmanagement.models.enums.Priority;
import com.project.oop.tasksmanagement.models.enums.StorySize;
import com.project.oop.tasksmanagement.models.enums.StoryStatus;
import com.project.oop.tasksmanagement.models.enums.TaskType;
import com.project.oop.tasksmanagement.utils.EventLog;

import java.util.ArrayList;
import java.util.List;


public class StoryImpl extends TaskImpl implements Story, Commentable {


    private final StoryStatus storyStatus;
    private final Priority priority;
    private final StorySize storySize;
    private String assignee;
    private TaskType taskType;

    public StoryImpl(int id, String title, String description, StorySize storySize) {
        super(id, title, description);
        this.storySize = storySize;
        this.priority = Priority.LOW;
        this.storyStatus = StoryStatus.NOT_DONE;
        assignee = "Not assigned";
        this.activityHistory.add(new EventLog("Story with %d is created".formatted(id)));
    }

    @Override
    public String getStatus() {
        return null;
    }


    @Override
    public StorySize getStorySize() {
        return storySize;
    }

    @Override
    public Priority getPriority() {
        return priority;
    }

    @Override
    public StoryStatus getStoryStatus() {
        return storyStatus;
    }

    @Override
    public List<EventLog> getActivityHistory() {
        return new ArrayList<>(activityHistory);
    }

    @Override
    public TaskType getTaskType() {
        return taskType = TaskType.STORY;
    }

}
