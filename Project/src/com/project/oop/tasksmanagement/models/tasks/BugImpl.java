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
    private String assignee;


    public BugImpl(int id, String title, String description, Severity severity) {
        super(id, title, description);
        this.severity = severity;
        priority = Priority.LOW;
        assignee = "Not assigned";
        status = BugStatus.ACTIVE;
        stepsToReproduce = new ArrayList<>();
    }

    @Override
    public Severity getSeverity() {
        return this.severity;
    }

    @Override
    public Priority getPriority() {
        return this.priority;
    }

    @Override
    public String getAssignee() {
        return this.assignee;
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
    public void changeBugPriority(Priority priority){
        activityHistory.add(new EventLog("The priority of the bug with ID %d switched from %s to %s.".formatted(getId(),this.priority,priority)));
        this.priority = priority;
    }
    public void changeBugSeverity(Severity severity){
        activityHistory.add(new EventLog("The severity of the bug with ID %d switched from %s to %s.".formatted(getId(),this.severity,severity)));
        this.severity = severity;
    }
    public void changeBugStatus(BugStatus status){
        activityHistory.add(new EventLog("The status of the bug with ID %d switched from %s to %s.".formatted(getId(),this.status,status)));
        this.status = status;

    }
    public void assignBugTo(Developer developer){
        activityHistory.add(new EventLog("The bug with ID %d has been assigned to %s".formatted(getId(),developer.getName())));
        this.assignee = developer.getName();
    }
    public void unAssignBug(){
        activityHistory.add(new EventLog("The bug with ID %d has been unassigned".formatted(getId())));
        this.assignee = "Not assigned";
    }
}
