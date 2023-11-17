package com.project.oop.tasksmanagement.models.contracts;
import com.project.oop.tasksmanagement.models.enums.BugStatus;
import com.project.oop.tasksmanagement.models.enums.Priority;
import com.project.oop.tasksmanagement.models.enums.Severity;

import java.util.List;

public interface Bug extends Task{

    List<String> getStepsToReproduce();

    Priority getPriority();

    Severity getSeverity();

    String getAssignee();

    void addStepToReproduce(String step);

    void changeBugPriority(Priority priority);

    void changeBugSeverity(Severity severity);

    void changeBugStatus(BugStatus status);

    void assignBugTo(Developer developer);

    void unAssignBug();
}
