package com.project.oop.tasksmanagement.models.tasks;

import com.project.oop.tasksmanagement.models.contracts.Bug;
import com.project.oop.tasksmanagement.models.enums.BugStatus;
import com.project.oop.tasksmanagement.models.enums.Severity;
import com.project.oop.tasksmanagement.models.enums.TaskType;
import com.project.oop.tasksmanagement.utils.EventLog;

import java.util.ArrayList;
import java.util.List;

public class BugImpl extends AssignableTaskImpl implements Bug {

    private final List<String> stepsToReproduce;
    private Severity severity;
    private BugStatus status;
    TaskType taskType;


    public BugImpl(int id, String title, String description, Severity severity) {
        super(id, title, description);
        this.severity = severity;
        status = BugStatus.ACTIVE;
        stepsToReproduce = new ArrayList<>();
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
    public List<String> getStepsToReproduce() {
        return new ArrayList<>(stepsToReproduce);
    }


    public void addStepToReproduce(String step){
        stepsToReproduce.add(step);
        activityHistory.add(new EventLog("Step %d to reproduce the bug added".formatted(stepsToReproduce.size())));
    }


    public void changeBugSeverity(Severity severity){
        activityHistory.add(new EventLog("The severity of the bug with ID %d switched from %s to %s.".formatted(getId(),getSeverity(),severity)));
        this.severity = severity;
    }

    public void changeBugStatus(BugStatus status){
        activityHistory.add(new EventLog("The status of the bug with ID %d switched from %s to %s.".formatted(getId(),getStatus(),status)));
        this.status = status;
    }

}
