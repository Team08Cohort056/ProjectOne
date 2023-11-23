package com.project.oop.tasksmanagement.models.contracts;

import com.project.oop.tasksmanagement.models.enums.Status;
import com.project.oop.tasksmanagement.models.enums.TaskType;

public interface Task extends Commentable, Identifiable, ActivityHistory{
    String getTitle();
    String getDescription();
    Status getStatus();
    TaskType getTaskType();

    void addComment(Comment comment);

    void removeComment(Comment comment);

}
