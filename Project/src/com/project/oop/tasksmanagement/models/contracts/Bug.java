package com.project.oop.tasksmanagement.models.contracts;
import com.project.oop.tasksmanagement.models.enums.Severity;
import com.project.oop.tasksmanagement.models.enums.Status;

public interface Bug extends AssignabelTask{

    String getStepsToReproduce();

    Severity getSeverity();

    void addStepToReproduce(String step);

    void changeBugSeverity(Severity severity);

    void changeBugStatus(Status status);

}
