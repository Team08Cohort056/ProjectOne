package com.project.oop.tasksmanagement.models;

import com.project.oop.tasksmanagement.models.contracts.*;
import com.project.oop.tasksmanagement.utils.EventLog;
import com.project.oop.tasksmanagement.utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

public class MemberImpl implements Member {
    private final static int MIN_NAME_LEN = 5;
    private final static int MAX_NAME_LEN = 15;
    private static final String INVALID_NAME_LEN = format(
            "Name length must be between %d and %d!",
            MIN_NAME_LEN,
            MAX_NAME_LEN);
    private final static String USERNAME_ALREADY_EXIST = "Username is already taken. Please choose another username!";

    private String name;
    private final List<Task> tasks;
    private final List<EventLog> activityHistories;

    public MemberImpl(String name) {
        setName(name);
        validateUniqueName(name);
        tasks = new ArrayList<>();
        activityHistories = new ArrayList<>();
    }


    @Override
    public String getName() {
        validateNameLength(name);
        return null;
    }

    private void setName(String name) {
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
    }

    @Override
    public void removeComment(Comment commentToRemove, Task taskToRemoveComment) {
        taskToRemoveComment.removeComment(commentToRemove);
    }

    // TODO
    @Override
    public String printTasks() {
        return null;
    }

    // TODO
    @Override
    public List<EventLog> getActivityHistory() {
        return new ArrayList<>(activityHistories);
    }

    private void validateNameLength(String name) {
        ValidationHelpers.validateStringLength(name, MIN_NAME_LEN, MAX_NAME_LEN, INVALID_NAME_LEN);
    }

    private void validateUniqueName(String name) {
        // TODO
    }
}
