package com.project.oop.tasksmanagement.models.contracts;

import com.project.oop.tasksmanagement.models.enums.Priority;

public interface AssignabelTask extends Task{

    Priority getPriority();

    String getAssignee();

    void changePriority(Priority priority);

    void assignTaskTo(String developer);

    void unAssignTask();
}
