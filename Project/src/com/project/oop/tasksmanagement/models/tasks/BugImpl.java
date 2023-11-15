package com.project.oop.tasksmanagement.models.tasks;

import com.project.oop.tasksmanagement.models.contracts.Bug;
import com.project.oop.tasksmanagement.models.contracts.Developer;
import com.project.oop.tasksmanagement.models.enums.BugStatus;
import com.project.oop.tasksmanagement.models.enums.Priority;
import com.project.oop.tasksmanagement.models.enums.Severity;
import com.project.oop.tasksmanagement.utils.EventLog;

import java.util.ArrayList;
import java.util.List;

public class BugImpl extends TaskImpl implements Bug {

    private final List<String> stepsToReproduce;
    private Priority priority;
    private Severity severity;
    private BugStatus status;
    private Developer assignee;


    public BugImpl(int id, String title, String description, Priority priority,Severity severity, Developer assignee) {
        super(id, title, description);
        setPriority(priority);
        setSeverity(severity);
        status = BugStatus.ACTIVE;
        setAssignee(assignee);
        stepsToReproduce = new ArrayList<>();
    }

    @Override
    public List<String> getStepsToReproduce() {
        return new ArrayList<>(stepsToReproduce);
    }

    @Override
    public Priority getPriority() {
        return this.priority;
    }

    private void setPriority(Priority priority) {
        this.priority = priority;
    }

    @Override
    public Severity getSeverity() {
        return this.severity;
    }

    private void setSeverity(Severity severity) {
        this.severity = severity;
    }

    @Override
    public Developer getAssignee() {
        return this.assignee;
    }

    public void setAssignee(Developer assignee) {
        this.assignee = assignee;
    }

    @Override
    public String getStatus() {
        return this.status.toString();
    }

    private void setStatus(BugStatus status) {
        this.status = status;
    }

    public void addStepToReproduce(String step){
        stepsToReproduce.add(step);
        activityHistory.add(new EventLog("Step %d to reproduce the bug added".formatted(stepsToReproduce.size())));
    }
    public void changeBugPriority(Priority priority){
        activityHistory.add(new EventLog("The priority of item with ID %d switched from %s to %s.".formatted(getId(),this.priority,priority)));
        this.priority = priority;
    }
    public void changeBugSeverity(Severity severity){
        activityHistory.add(new EventLog("The severity of item with ID %d switched from %s to %s.".formatted(getId(),this.severity,severity)));
        this.severity = severity;
    }
    public void changeBugStatus(BugStatus status){
        activityHistory.add(new EventLog("The status of item with ID %d switched from %s to %s.".formatted(getId(),this.status,status)));
        this.status = status;

    }
}
