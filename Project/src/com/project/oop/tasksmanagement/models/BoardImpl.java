package com.project.oop.tasksmanagement.models;

import com.project.oop.tasksmanagement.models.contracts.Board;
import com.project.oop.tasksmanagement.utils.EventLog;

import java.util.ArrayList;
import java.util.List;

public class BoardImpl implements Board {
    private final int MIN_NAME_LENGTH = 5;
    private final int MAX_NAME_LENGTH = 10;
    private String name;
//    private final List <Task> tasks;
   private final List<EventLog> activityHistory;

    public BoardImpl(String name) {
        setName(name);
        //this.tasks = new ArrayList<>();
        this.activityHistory = new ArrayList<>();
        activityHistory.add(new EventLog("Board %s created".formatted(name)));
    }

    @Override
    public String getName() {
        return this.name;
    }


    private void setName(String name) {
        //Validation unique and length needed
        this.name = name;
    }
    //    @Override
//    public List<Task> getTasks() {
//        return new ArrayList<>(tasks);
//    }

//    @Override
//    public void addTask(Task task) {
//        tasks.add(task);
//        activityHistory.add(new EventLog("%s %s added to the board".formatted(task.getType(),task.getName())));
//    }

    @Override
    public List<EventLog> getActivityHistory() {
        return new ArrayList<>(this.activityHistory);
    }
}
