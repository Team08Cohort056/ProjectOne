package com.project.oop.tasksmanagement.models.enums;

public enum Status {
    ACTIVE,DONE,NOT_DONE,IN_PROGRESS,UNSCHEDULED,SCHEDULED,NEW;

    @Override
    public String toString() {
        switch (this){
            case ACTIVE:
                return "Active";
            case DONE:
                return "Done";
            case NOT_DONE:
                return "Not done";
            case IN_PROGRESS:
                return "In progress";
            case UNSCHEDULED:
                return "Unscheduled";
            case SCHEDULED:
                return "Scheduled";
            case NEW:
                return "New";
            default:
               throw new UnsupportedOperationException("Can't convert status");
        }
    }
}
