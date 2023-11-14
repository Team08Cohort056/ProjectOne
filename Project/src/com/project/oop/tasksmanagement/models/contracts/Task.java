package com.project.oop.tasksmanagement.models.contracts;

import com.project.oop.tasksmanagement.models.enums.TaskType;

public interface Task extends Commentable, Identifiable, ActivityHistory{
    String getTitle();
    String getDescription();
    String getStatus();
    TaskType getTaskType();

    void addComment(Comment comment);
    void removeComment(Comment comment);

}
