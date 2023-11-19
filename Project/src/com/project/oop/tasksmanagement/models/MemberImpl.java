package com.project.oop.tasksmanagement.models;

import com.project.oop.tasksmanagement.core.exceptions.InvalidUserInputException;
import com.project.oop.tasksmanagement.models.contracts.*;
import com.project.oop.tasksmanagement.utils.EventLog;
import com.project.oop.tasksmanagement.utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

public class MemberImpl implements Member {
    private final static int MIN_NAME_LEN = 5;
    private final static int MAX_NAME_LEN = 15;
    private final static String NO_TASKS_FOR_THE_MEMBER = "There are no tasks assigned to the member %s!";
    private static final String INVALID_NAME_LEN = format(
            "Name length must be between %d and %d!",
            MIN_NAME_LEN,
            MAX_NAME_LEN);

    private String name;
    private final List<Task> tasks;
    private final List<EventLog> activityHistory;

    public MemberImpl(String name) {
        setName(name);
        tasks = new ArrayList<>();
        activityHistory = new ArrayList<>();
        activityHistory.add(new EventLog("Member %s created".formatted(name)));
    }

    @Override
    public String getName() {
        return name;
    }

    private void setName(String name) {
        validateNameLength(name);
        this.name = name;
    }

    @Override
    public List<Task> getTasks() {
        return new ArrayList<>(tasks);
    }

    @Override
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    @Override
    public void removeTask(Task task) {
        this.tasks.remove(task);
    }

    @Override
    public void addComment(Comment commentToAdd, Task taskToAddComment) {
        taskToAddComment.addComment(commentToAdd);
        activityHistory.add(new EventLog("Comment [%s] added".formatted(commentToAdd.getContent())));
    }

    @Override
    public void removeComment(Comment commentToRemove, Task taskToRemoveComment) {
        taskToRemoveComment.removeComment(commentToRemove);
        activityHistory.add(new EventLog("Comment [%s] removed".formatted(commentToRemove)));
    }

    @Override
    public String printTasks() {
        StringBuilder result = new StringBuilder();
        int counter = 1;
        if (tasks.isEmpty()) {
            throw new InvalidUserInputException(String.format(NO_TASKS_FOR_THE_MEMBER, getName()));
        }
        for (Task task : tasks) {
            result.append(counter).append(".").append(task.getId()).append(task.getTaskType()).append(task.getStatus())
                    .append(System.lineSeparator());
            counter++;
        }
        return result.toString();
    }

    @Override
    public List<EventLog> getActivityHistory() {
        return new ArrayList<>(activityHistory);
    }

    private void validateNameLength(String name) {
        ValidationHelpers.validateStringLength(name, MIN_NAME_LEN, MAX_NAME_LEN, INVALID_NAME_LEN);
    }
}
