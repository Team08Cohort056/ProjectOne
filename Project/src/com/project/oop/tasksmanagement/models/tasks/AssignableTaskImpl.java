package com.project.oop.tasksmanagement.models.tasks;

import com.project.oop.tasksmanagement.models.contracts.AssignabelTask;
import com.project.oop.tasksmanagement.models.enums.Priority;
import com.project.oop.tasksmanagement.utils.EventLog;

public abstract class AssignableTaskImpl extends TaskImpl implements AssignabelTask {

    private static final String PRIORITY_SWITCHED_MESSAGE = "The priority of the %s with ID %d switched from %s to %s.";
    private static final String ASSIGNED_EVENT = "The %s with ID %d has been assigned to %s";
    private static final String UNASSIGNED_EVENT = "The %s with ID %d has been unassigned";
    private static final String NOT_ASSIGNED = "Not assigned";
    private Priority priority;
    private String assignee;

    public AssignableTaskImpl(int id, String title, String description) {
        super(id, title, description);
        this.priority = Priority.LOW;
        assignee = NOT_ASSIGNED;
    }

    public Priority getPriority() {
        return this.priority;
    }

    @Override
    public String getAssignee() {
        return assignee;
    }

    public void changePriority(Priority priority){
        activityHistory.add(new EventLog(PRIORITY_SWITCHED_MESSAGE.formatted(getTaskType(),getId(),this.priority,priority)));
        this.priority = priority;
    }
    public void assignTaskTo(String developer){
        activityHistory.add(new EventLog(ASSIGNED_EVENT.formatted(getTaskType(),getId(),developer)));
        this.assignee = developer ;
    }

    public void unAssignTask(){
        activityHistory.add(new EventLog(UNASSIGNED_EVENT.formatted(getTaskType(),getId())));
        this.assignee = NOT_ASSIGNED;
    }
}
