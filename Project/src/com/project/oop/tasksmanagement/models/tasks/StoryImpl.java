package com.project.oop.tasksmanagement.models.tasks;

import com.project.oop.tasksmanagement.models.contracts.Commentable;
import com.project.oop.tasksmanagement.models.contracts.Story;
import com.project.oop.tasksmanagement.models.enums.*;
import com.project.oop.tasksmanagement.utils.EventLog;

import java.util.ArrayList;
import java.util.List;


public class StoryImpl extends TaskImpl implements Story {


    private StorySize storySize;
    private Priority priority;
    private StoryStatus storyStatus;
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
        return this.storyStatus.toString();
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

    public void changeStorySize(StorySize storySize){
        activityHistory.add(new EventLog("The size of the story with ID %d switched from %s to %s.".formatted(getId(),this.storySize,storySize)));
        this.storySize = storySize;
    }

    public void changeStoryPriority(Priority priority){
        activityHistory.add(new EventLog("The priority of the story with ID %d switched from %s to %s.".formatted(getId(),this.priority,priority)));
        this.priority = priority;
    }

    public void changeStoryStatus(StoryStatus status){
        activityHistory.add(new EventLog("The status of the story with ID %d switched from %s to %s.".formatted(getId(),this.storyStatus,status)));
        this.storyStatus = status;
    }
}
