package com.project.oop.tasksmanagement.models.tasks;

import com.project.oop.tasksmanagement.models.contracts.Feedback;
import com.project.oop.tasksmanagement.models.enums.BugStatus;
import com.project.oop.tasksmanagement.models.enums.FeedbackStatus;
import com.project.oop.tasksmanagement.models.enums.Severity;
import com.project.oop.tasksmanagement.models.enums.TaskType;
import com.project.oop.tasksmanagement.utils.EventLog;

public class FeedbackImpl extends TaskImpl implements Feedback {

    private TaskType taskType;
    private int rating;
    //will store the rating of the task.
    private FeedbackStatus feedbackStatus;
    //will store the status of the task.
    public FeedbackImpl(int id, String title, String description, int rating) {
        super(id, title, description);
        setRating(rating);
        feedbackStatus = FeedbackStatus.NEW;

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
    public TaskType getTaskType() {
        return taskType = TaskType.FEEDBACK;
    }

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

    public void changeFeedbackRating(int rating){
        activityHistory.add(new EventLog("The rating of the feedback with ID %d switched from %d to %d.".formatted(getId(),this.rating,rating)));
        this.rating = rating;
    }

    public void changeFeedbackStatus(FeedbackStatus status){
        activityHistory.add(new EventLog("The status of the feedback with ID %d switched from %s to %s.".formatted(getId(),this.feedbackStatus,status)));
        this.feedbackStatus = status;
    }
}
