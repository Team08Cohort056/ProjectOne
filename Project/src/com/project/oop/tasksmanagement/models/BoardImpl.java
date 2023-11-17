package com.project.oop.tasksmanagement.models;

import com.project.oop.tasksmanagement.models.contracts.Board;
import com.project.oop.tasksmanagement.models.contracts.Task;
import com.project.oop.tasksmanagement.utils.EventLog;
import com.project.oop.tasksmanagement.utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;

public class BoardImpl implements Board {
    private static final String BOARD_NAME_ERR = "Board name should be between %d and %d symbols.";
    private final int MIN_NAME_LENGTH = 5;
    private final int MAX_NAME_LENGTH = 10;
    private String name;
   private final List <Task> boardTasks;
   private final List<EventLog> activityHistory;

    public BoardImpl(String name) {
        setName(name);
        this.boardTasks = new ArrayList<>();
        this.activityHistory = new ArrayList<>();
        activityHistory.add(new EventLog("Board %s created".formatted(name)));
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public List<Task> getBoardTasks() {
        return new ArrayList<>(boardTasks);
    }

    @Override
    public List<EventLog> getActivityHistory() {
        return new ArrayList<>(this.activityHistory);
    }

    private void setName(String name) {
        ValidationHelpers.validateStringLength(name,MIN_NAME_LENGTH,MAX_NAME_LENGTH, BOARD_NAME_ERR.formatted(MIN_NAME_LENGTH,MAX_NAME_LENGTH));
        this.name = name;
    }

    @Override
    public void addBoardTask(Task task) {
        boardTasks.add(task);
        activityHistory.add(new EventLog("%s with ID %d added to the board".formatted(task.getTaskType(),task.getId())));
    }
}
