package com.project.oop.tasksmanagement.models.contracts;

import java.util.List;

public interface Commentable extends Comment {
    List<Comment> getComments();
}
