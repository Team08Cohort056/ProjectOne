package com.project.oop.tasksmanagement.models.tasks;

import com.project.oop.tasksmanagement.models.contracts.Feedback;
import com.project.oop.tasksmanagement.models.enums.FeedbackStatus;

public class FeedbackImpl extends TaskImpl implements Feedback {

    private int rating;
    //will store the rating of the task.
    private FeedbackStatus feedbackStatus;
    //will store the status of the task.
    public FeedbackImpl(int id, String title, String description,FeedbackStatus status, int rating) {
        super(id, title, description);
        setRating(rating);
        setFeedbackStatus(status);

    }
    //Constructor creates a task with the addition of a rating and a custom status being set.
    public void setFeedbackStatus(FeedbackStatus status) {
        this.feedbackStatus = status;
    }
    //sets status of the Feedback.

    public void setRating(int rating) {
        this.rating = rating;
    }
    //sets rating of the Feedback.


    @Override
    public int getRating() {
        return this.rating;
    }
    //returns the rating as an Integer.
    @Override
    public FeedbackStatus getFeedbackStatus() {
        return feedbackStatus;
    }
    //returns the status as FeedbackStatus.


    @Override
    public String getStatus() {
        return null;
    }
    //TODO
}
