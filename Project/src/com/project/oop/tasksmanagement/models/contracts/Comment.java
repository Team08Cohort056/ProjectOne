package com.project.oop.tasksmanagement.models.contracts;

public interface Comment {
    String getContent();

    String getAuthor();

    public String commentsAsString();
}
