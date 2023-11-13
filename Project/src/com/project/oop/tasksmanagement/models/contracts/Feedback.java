package com.project.oop.tasksmanagement.models.contracts;

import com.project.oop.tasksmanagement.models.enums.FeedbackStatus;

public interface Feedback {
    int getRating();
    FeedbackStatus getFeedbackStatus();
}
