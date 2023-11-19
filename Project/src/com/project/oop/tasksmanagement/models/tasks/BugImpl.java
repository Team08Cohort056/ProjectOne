package com.project.oop.tasksmanagement.models.tasks;

import com.project.oop.tasksmanagement.models.contracts.Bug;
import com.project.oop.tasksmanagement.models.enums.BugStatus;
import com.project.oop.tasksmanagement.models.enums.Severity;
import com.project.oop.tasksmanagement.models.enums.TaskType;
import com.project.oop.tasksmanagement.utils.EventLog;

public class BugImpl extends AssignableTaskImpl implements Bug {

    private static final String SEVERITY_SWITCHED_MESSAGE = "The severity of the bug with ID %d switched from %s to %s.";
    private static final String STATUS_SWITCHED_MESSAGE = "The status of the bug with ID %d switched from %s to %s.";
    private static final String STEPS_REPRODUCE_THE_BUG_ADDED = "Steps to reproduce the bug added";
    private static final String NO_STEPS_TO_REPRODUCE_BUG = "No steps added";
    private String stepsToReproduce;
    private Severity severity;
    private BugStatus status;
    private TaskType taskType;


    public BugImpl(int id, String title, String description, Severity severity) {
        super(id, title, description);
        this.severity = severity;
        status = BugStatus.ACTIVE;
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
    public String getStatus() {
        return this.status.toString();
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

    public void changeBugStatus(BugStatus status){
        activityHistory.add(new EventLog(STATUS_SWITCHED_MESSAGE.formatted(getId(),getStatus(),status)));
        this.status = status;
    }

}
