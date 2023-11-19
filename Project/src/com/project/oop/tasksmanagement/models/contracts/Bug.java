package com.project.oop.tasksmanagement.models.contracts;
import com.project.oop.tasksmanagement.models.enums.BugStatus;
import com.project.oop.tasksmanagement.models.enums.Severity;

public interface Bug extends AssignabelTask{

    String getStepsToReproduce();

    Severity getSeverity();

    void addStepToReproduce(String step);

    void changeBugSeverity(Severity severity);

    void changeBugStatus(BugStatus status);

}
