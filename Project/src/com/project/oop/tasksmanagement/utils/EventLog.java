package com.project.oop.tasksmanagement.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class EventLog {
    private final String description;
    private final LocalDateTime timestamp;

    public EventLog(String description){
        this.description = description;
        this.timestamp = LocalDateTime.now();
    }

    public String getDescription() {
        return description;
    }

    public String viewInfo() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMMM-yyyy HH:mm:ss");
        String date = timestamp.format(formatter);
        return "[%s] %s".formatted(date,getDescription());
    }

}
