package com.project.oop.tasksmanagement.models.tasks;

import com.project.oop.tasksmanagement.models.contracts.Commentable;
import com.project.oop.tasksmanagement.models.contracts.Story;
import com.project.oop.tasksmanagement.models.enums.*;
import com.project.oop.tasksmanagement.utils.EventLog;

import java.util.ArrayList;
import java.util.List;

public class StoryImpl extends AssignableTaskImpl implements Story {

    private StorySize storySize;
    private StoryStatus storyStatus;
    private TaskType taskType;

    public StoryImpl(int id, String title, String description, StorySize storySize) {
        super(id, title, description);
        this.storySize = storySize;
        this.storyStatus = StoryStatus.NOT_DONE;
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
    public TaskType getTaskType() {
        return taskType = TaskType.STORY;
    }

    public void changeStorySize(StorySize storySize) {
        activityHistory.add(new EventLog("The size of the story with ID %d switched from %s to %s."
                .formatted(getId(), this.storySize, storySize)));
        this.storySize = storySize;
    }

    public void changeStoryStatus(StoryStatus status) {
        activityHistory.add(new EventLog("The status of the story with ID %d switched from %s to %s."
                .formatted(getId(), this.storyStatus, status)));
        this.storyStatus = status;
    }

}
