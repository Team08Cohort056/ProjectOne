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
    private TaskType taskType;
    private final List<EventLog> activityHistory;

    public StoryImpl(int id, String title, String description) {
        super(id, title, description);
        activityHistory = new ArrayList<>();
        this.storyStatus = getStoryStatus();
        this.taskType = getTaskType();
        this.priority = getPriority();
        this.storySize = getStorySize();
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
