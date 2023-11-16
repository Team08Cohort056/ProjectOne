package com.project.oop.tasksmanagement.models.contracts;

import com.project.oop.tasksmanagement.models.enums.FeedbackStatus;

public interface Feedback extends Task{
    int getRating();
    FeedbackStatus getFeedbackStatus();
}
