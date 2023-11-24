package com.project.oop.tasksmanagement.models.tasks;

import com.project.oop.tasksmanagement.models.contracts.Comment;
import com.project.oop.tasksmanagement.models.contracts.Feedback;
import com.project.oop.tasksmanagement.models.enums.*;
import com.project.oop.tasksmanagement.utils.EventLog;

public class FeedbackImpl extends TaskImpl implements Feedback {

    private static final String FEEDBACK_RATING_CHANGED_SUCCESSFULLY = "The rating of the feedback with ID %d switched from %d to %d.";
    private static final String FEEDBACK_STATUS_CHANGED_SUCCESSFULLY = "The status of the feedback with ID %d switched from %s to %s.";
    public static final String CHECK_STATUS_MESSAGE = "This status is not suitable for task type Feedback";
    private TaskType taskType;
    private int rating;
    private Status status;
    public FeedbackImpl(int id, String title, String description, int rating) {
        super(id, title, description);
        setRating(rating);
        status = Status.NEW;

    }



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
    public Status getStatus() {
        return status;
    }

    public void changeFeedbackRating(int rating){
        activityHistory.add(new EventLog(FEEDBACK_RATING_CHANGED_SUCCESSFULLY.formatted(getId(),this.rating,rating)));
        setRating(rating);
    }

    public void changeFeedbackStatus(Status status){
        checkStatusForTask(status);
        activityHistory.add(new EventLog(FEEDBACK_STATUS_CHANGED_SUCCESSFULLY.formatted(getId(),this.status,status)));
        setFeedbackStatus(status);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("Rating: %d".formatted(getRating())).append(System.lineSeparator());
        sb.append("Comments:").append(System.lineSeparator());
        if (getComments().isEmpty()){
            sb.append("No comments has been added to this %s yet."
                    .formatted(getTaskType().toString())).append(System.lineSeparator());
        } else {
            int counter = 1;
            for (Comment comment:getComments()) {
                sb.append("%d. %s".formatted(counter,comment.commentsAsString())).append(System.lineSeparator());
                counter++;
            }
        }
        sb.append("----------");
        return sb.toString();
    }
    @Override
    protected void checkStatusForTask(Status status) {
        switch (status) {
            case NEW, UNSCHEDULED, SCHEDULED, DONE:
                break;
            default:
                throw new IllegalArgumentException(CHECK_STATUS_MESSAGE);
        }
    }
    private void setFeedbackStatus(Status status) {
        this.status = status;
    }

    private void setRating(int rating) {
        this.rating = rating;
    }
}
