package com.project.oop.tasksmanagement.models.contracts;
import com.project.oop.tasksmanagement.models.enums.BugStatus;
import com.project.oop.tasksmanagement.models.enums.Priority;
import com.project.oop.tasksmanagement.models.enums.Severity;

import java.util.List;

public interface Bug extends AssignabelTask{

    List<String> getStepsToReproduce();

    Severity getSeverity();

    void addStepToReproduce(String step);

    void changeBugSeverity(Severity severity);

    void changeBugStatus(BugStatus status);

}
