package com.project.oop.tasksmanagement.models.tasks;

import com.project.oop.tasksmanagement.models.contracts.Comment;
import com.project.oop.tasksmanagement.models.contracts.Commentable;
import com.project.oop.tasksmanagement.models.contracts.Story;
import com.project.oop.tasksmanagement.models.enums.*;
import com.project.oop.tasksmanagement.utils.EventLog;

import java.util.ArrayList;
import java.util.List;

public class StoryImpl extends AssignableTaskImpl implements Story {

    public static final String CHECK_STATUS_MESSAGE = "This status is not suitable for task type Story";
    private StorySize storySize;
    private Status status;
    private TaskType taskType;

    public StoryImpl(int id, String title, String description, StorySize storySize) {
        super(id, title, description);
        this.storySize = storySize;
        this.status = Status.NOT_DONE;
        this.activityHistory.add(new EventLog("Story with %d is created".formatted(id)));
    }

    @Override
    public Status getStatus() {
        return this.status;
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

    public void changeStoryStatus(Status status) {
        checkStatusForTask(status);
        activityHistory.add(new EventLog("The status of the story with ID %d switched from %s to %s."
                .formatted(getId(), this.status, status)));
        this.status = status;
    }

    @Override
    protected void checkStatusForTask(Status status) {
        switch (status) {
            case NOT_DONE,IN_PROGRESS, DONE:
                break;
            default:
                throw new IllegalArgumentException(CHECK_STATUS_MESSAGE);
        }
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("Size: %s".formatted(getStorySize().toString())).append(System.lineSeparator());
        sb.append("Comments:").append(System.lineSeparator());
        if (getComments().isEmpty()){
            sb.append("No comments has been added to this %s yet.".formatted(getTaskType().toString()));
        } else {
            int counter = 1;
            for (Comment comment:getComments()) {
                sb.append("%d. %s".formatted(counter,comment.commentsAsString())).append(System.lineSeparator());
                counter++;
            }
        }
        return sb.toString();
    }

}
