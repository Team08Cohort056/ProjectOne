package com.project.oop.tasksmanagement.models.tasks;

import com.project.oop.tasksmanagement.models.contracts.Comment;
import com.project.oop.tasksmanagement.models.contracts.Task;
import com.project.oop.tasksmanagement.models.enums.Status;
import com.project.oop.tasksmanagement.models.enums.TaskType;
import com.project.oop.tasksmanagement.utils.EventLog;
import com.project.oop.tasksmanagement.utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;

public abstract class TaskImpl implements Task {

    private static final int MIN_TITLE_LENGTH = 10;
    private static final int MAX_TITLE_LENGTH = 100;
    private static final String TITLE_LENGTH_ERR = "Title should be between %d and %d symbols.";
    private static final int MIN_DESC_LENGTH = 10;
    private static final int MAX_DESC_LENGTH = 500;
    private static final String DESC_LENGTH_ERR = "Description should be between %d and %d symbols.";
    private final int id;
    private String title;
    private String description;
    private Status status;
    protected List<EventLog> activityHistory;
    private final List<Comment> comments;
    private TaskType taskType;


    public TaskImpl(int id, String title, String description) {
        this.id = id;
        setTitle(title);
        setDescription(description);
        status = getStatus();
        activityHistory = new ArrayList<>();
        comments = new ArrayList<>();
        activityHistory.add(new EventLog("New %s created.".formatted(getTaskType())));

    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    public abstract TaskType getTaskType();

    @Override
    public abstract Status getStatus();

    @Override
    public List<EventLog> getActivityHistory() {
        return new ArrayList<>(activityHistory);
    }

    @Override
    public List<Comment> getComments() {
        return new ArrayList<>(comments);
    }

    private void setTitle(String title) {
        ValidationHelpers.validateStringLength(title, MIN_TITLE_LENGTH, MAX_TITLE_LENGTH,
                String.format(TITLE_LENGTH_ERR, MIN_TITLE_LENGTH, MAX_TITLE_LENGTH));
        this.title = title;
    }

    private void setDescription(String description) {
        ValidationHelpers.validateStringLength(description, MIN_DESC_LENGTH, MAX_DESC_LENGTH, DESC_LENGTH_ERR.formatted(MIN_DESC_LENGTH, MAX_DESC_LENGTH));
        this.description = description;
    }

    protected abstract void checkStatusForTask(Status status);

    @Override
    public void addComment(Comment comment) {
        comments.add(comment);
    }

    @Override
    public void removeComment(Comment comment) {
        comments.remove(comment);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getTaskType().toString()).append(System.lineSeparator());
        sb.append("ID %d".formatted(getId())).append(System.lineSeparator());
        sb.append("Title: %s".formatted(getTitle())).append(System.lineSeparator());
        sb.append("Description: %s".formatted(getDescription())).append(System.lineSeparator());
        sb.append("Status: %s".formatted(getStatus().toString())).append(System.lineSeparator());
        return sb.toString();
    }
}

