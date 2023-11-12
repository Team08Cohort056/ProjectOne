package com.project.oop.tasksmanagement.models.contracts;

import java.util.List;

public interface Member extends ActivityHistory {
    String getName();


    List<Task> getTasks();

    void addTask(Task task);

    void removeTask(Task task);

    void addComment(Comment commentToAdd, Task taskToAddComment);

    void removeComment(Comment commentToRemove, Task taskToRemoveComment);

    String printTasks();

}
