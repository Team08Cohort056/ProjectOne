package com.project.oop.tasksmanagement.models.contracts;

import com.project.oop.tasksmanagement.models.enums.Status;

public interface Feedback extends Task{
    int getRating();

    void changeFeedbackRating(int rating);

    void changeFeedbackStatus(Status status);
}
