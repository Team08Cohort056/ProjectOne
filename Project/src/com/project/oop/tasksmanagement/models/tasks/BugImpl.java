package com.project.oop.tasksmanagement.models.tasks;

import com.project.oop.tasksmanagement.models.contracts.Bug;
import com.project.oop.tasksmanagement.models.contracts.Comment;
import com.project.oop.tasksmanagement.models.enums.Severity;
import com.project.oop.tasksmanagement.models.enums.Status;
import com.project.oop.tasksmanagement.models.enums.TaskType;
import com.project.oop.tasksmanagement.utils.EventLog;

public class BugImpl extends AssignableTaskImpl implements Bug {

    private static final String SEVERITY_SWITCHED_MESSAGE = "The severity of the bug with ID %d switched from %s to %s.";
    private static final String STATUS_SWITCHED_MESSAGE = "The status of the bug with ID %d switched from %s to %s.";
    private static final String STEPS_REPRODUCE_THE_BUG_ADDED = "Steps to reproduce the bug added.";
    private static final String NO_STEPS_TO_REPRODUCE_BUG = "No steps to reproduce thee bug added yet.";
    public static final String CHECK_STATUS_MESSAGE = "This status is not suitable for task type Bug.";
    private String stepsToReproduce;
    private Severity severity;
    private Status status;
    private TaskType taskType;


    public BugImpl(int id, String title, String description, Severity severity) {
        super(id, title, description);
        this.severity = severity;
        status = Status.ACTIVE;
        stepsToReproduce = NO_STEPS_TO_REPRODUCE_BUG;
    }

    @Override
    public Severity getSeverity() {
        return this.severity;
    }

    @Override
    public TaskType getTaskType() {
      return taskType  = TaskType.BUG;
    }

    @Override
    public Status getStatus() {
        return this.status;
    }

    @Override
    public String getStepsToReproduce() {
        return stepsToReproduce;
    }


    public void addStepToReproduce(String step){
        stepsToReproduce = step;
        activityHistory.add(new EventLog(STEPS_REPRODUCE_THE_BUG_ADDED));
    }


    public void changeBugSeverity(Severity severity){
        activityHistory.add(new EventLog(SEVERITY_SWITCHED_MESSAGE.formatted(getId(),getSeverity(),severity)));
        this.severity = severity;
    }

    public void changeBugStatus(Status status){
        checkStatusForTask(status);
        activityHistory.add(new EventLog(STATUS_SWITCHED_MESSAGE.formatted(getId(),getStatus(),status)));
        this.status = status;
    }

    @Override
    protected void checkStatusForTask(Status status) {
        switch (status) {
            case ACTIVE, DONE:
                break;
            default:
                throw new IllegalArgumentException(CHECK_STATUS_MESSAGE);
        }
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("Severity: %s".formatted(getSeverity().toString())).append(System.lineSeparator());
        sb.append("Steps to reproduce the bug:").append(System.lineSeparator());
        sb.append(getStepsToReproduce()).append(System.lineSeparator());
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
}
