package com.project.oop.tasksmanagement.models.contracts;

import java.util.List;

public interface Board extends ActivityHistory {
    String getName();

    List<Task> getBoardTasks();

    void addBoardTask(Task task);
}
