package com.project.oop.tasksmanagement.models.enums;

public enum FeedbackStatus {
    NEW,UNSCHEDULED,SCHEDULED,DONE;

    public String toString() {
        switch (this) {
            case NEW:
                return "New";
            case UNSCHEDULED:
                return "Unscheduled";
            case SCHEDULED:
                return "Scheduled";
            case DONE:
                return "Done";
            default:
                throw new UnsupportedOperationException("Feedback status can be New, Unscheduled, Scheduled or Done.");

        }
}}
