package com.project.oop.tasksmanagement.models.contracts;

import com.project.oop.tasksmanagement.utils.EventLog;

import java.util.List;

public interface ActivityHistory {
    List<EventLog> getActivityHistory();
}
