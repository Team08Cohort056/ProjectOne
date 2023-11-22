package com.project.oop.tasksmanagement.models.tasks;

import com.project.oop.tasksmanagement.models.contracts.Comment;
import com.project.oop.tasksmanagement.models.contracts.Feedback;
import com.project.oop.tasksmanagement.models.enums.BugStatus;
import com.project.oop.tasksmanagement.models.enums.FeedbackStatus;
import com.project.oop.tasksmanagement.models.enums.Severity;
import com.project.oop.tasksmanagement.models.enums.TaskType;
import com.project.oop.tasksmanagement.utils.EventLog;

public class FeedbackImpl extends TaskImpl implements Feedback {

    private static final String FEEDBACK_RATING_CHANGED_SUCCESSFULLY = "The rating of the feedback with ID %d switched from %d to %d.";
    private static final String FEEDBACK_STATUS_CHANGED_SUCCESSFULLY = "The status of the feedback with ID %d switched from %s to %s.";
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
    private void setFeedbackStatus(FeedbackStatus status) {
        this.feedbackStatus = status;
    }
    //sets status of the Feedback.

    private void setRating(int rating) {
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
        activityHistory.add(new EventLog(FEEDBACK_RATING_CHANGED_SUCCESSFULLY.formatted(getId(),this.rating,rating)));
        setRating(rating);
    }

    public void changeFeedbackStatus(FeedbackStatus status){
        activityHistory.add(new EventLog(FEEDBACK_STATUS_CHANGED_SUCCESSFULLY.formatted(getId(),this.feedbackStatus,status)));
        setFeedbackStatus(status);
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("Rating: %d".formatted(getRating())).append(System.lineSeparator());
        sb.append("Status: %s".formatted(getStatus())).append(System.lineSeparator());
        sb.append("Comments:").append(System.lineSeparator());
        if (getComments().isEmpty()){
            sb.append("No comments has been added to this %s yet.".formatted(getTaskType().toString()));
        } else {
            int counter = 1;
            for (Comment comment:getComments()) {
                sb.append("%d. %s".formatted(counter,comment.commentsAsString())).append(System.lineSeparator());
                counter++;
            }
        }
        return sb.toString();
    }
}
