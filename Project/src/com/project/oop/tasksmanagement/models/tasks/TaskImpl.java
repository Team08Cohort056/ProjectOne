package com.project.oop.tasksmanagement.models.tasks;

import com.project.oop.tasksmanagement.models.contracts.Comment;
import com.project.oop.tasksmanagement.models.contracts.Member;
import com.project.oop.tasksmanagement.models.contracts.Task;
import com.project.oop.tasksmanagement.models.enums.TaskType;
import com.project.oop.tasksmanagement.utils.EventLog;
import com.project.oop.tasksmanagement.utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;

public abstract class TaskImpl implements Task {

    public static final int MIN_TITLE_LENGTH = 10;
    public static final int MAX_TITLE_LENGTH = 100;
    public static final String TITLE_LENGTH_ERR = "Title should be between %d and %d symbols.";
    public static final int MIN_DESC_LENGTH = 10;
    public static final int MAX_DESC_LENGTH = 500;
    public static final String DESC_LENGTH_ERR = "Description should be between %d and %d symbols.";
    private int id;
    private String title;
    private String description;
    protected List<EventLog> activityHistory;
    private List<Comment> comments;
    private TaskType taskType;


    public TaskImpl(int id,String title, String description) {
        setId(id);
        setTitle(title);
        setDescription(description);
        activityHistory=new ArrayList<>();
        comments=new ArrayList<>();
        activityHistory.add(new EventLog("New %s created.".formatted(getTaskType())));

    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        ValidationHelpers.validateStringLength(title, MIN_TITLE_LENGTH, MAX_TITLE_LENGTH,
                String.format(TITLE_LENGTH_ERR,MIN_TITLE_LENGTH,MAX_TITLE_LENGTH));
        this.title = title;
    }

    public void setDescription(String description) {
        ValidationHelpers.validateStringLength(description, MIN_DESC_LENGTH, MAX_DESC_LENGTH,DESC_LENGTH_ERR.formatted(MIN_DESC_LENGTH,MAX_DESC_LENGTH));
        this.description = description;
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

    public TaskType getTaskType() {
        return taskType=TaskType.BUG;
    }
    @Override
    public abstract String getStatus();

    @Override
    public List<EventLog> getActivityHistory() {
        return new ArrayList<>(activityHistory);
    }

    @Override
    public List<Comment> getComments() {
        return new ArrayList<>(comments);
    }

    @Override
    public void addComment(Comment comment) {
        comments.add(comment);
    }

    @Override
    public void removeComment(Comment comment) {
        comments.remove(comment);
    }

}
