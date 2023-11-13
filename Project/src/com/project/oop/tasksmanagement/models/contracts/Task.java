package com.project.oop.tasksmanagement.models.contracts;

public interface Task extends Commentable, Identifiable, ActivityHistory{
    void addComment(Comment comment);

    void removeComment(Comment comment);

    String getTitle();
    String getDescription();
    String getStatus();




}
